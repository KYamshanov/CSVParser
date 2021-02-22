package ru.undframe;

import org.reflections.Reflections;
import ru.undframe.field.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class CSVParser implements Parser {

    private Map<Class, CSVObject> csvObjects = new HashMap<>();
    private Map<Class, ru.undframe.field.Field> fieldParsers = new HashMap<>();


    @Override
    public void launch(String pac) {
        Reflections reflections = new Reflections(pac);
        try {
            Set<Class<?>> fieldParsers = reflections.getTypesAnnotatedWith(FieldParser.class);
            for (Class<?> fieldParser : fieldParsers) {
                if (Arrays.asList(fieldParser.getInterfaces()).contains(ru.undframe.field.Field.class)) {

                    ru.undframe.field.Field<?> field = (ru.undframe.field.Field) fieldParser.newInstance();
                    FieldParser parses = fieldParser.getDeclaredAnnotation(FieldParser.class);
                    for (Class aClass : parses.parseClasses()) {
                        this.fieldParsers.put(aClass, field);
                    }

                }
            }

            Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(CSVData.class);
            for (Class<?> aClass : typesAnnotatedWith) {
                for (Annotation declaredAnnotation : aClass.getDeclaredAnnotations()) {
                    if (declaredAnnotation instanceof CSVData) {
                        CSVObject csvObject = instanceCSVObject(aClass);
                        registerCSV(aClass, csvObject);
                    }
                }
            }

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static Parser getInstance() {
        return new CSVParser();
    }

    void registerCSV(Class c, CSVObject csv) {
        csvObjects.put(c, csv);
    }

    @Override
    public void refreshCSVs() {
        for (CSVObject csv : csvObjects.values()) {
            csv.refreshData();
        }
    }

    @Override
    public CSVObject getCSVObject(Class c) {
        return csvObjects.get(c);
    }

    private CSVObject instanceCSVObject(Class aClass) throws IllegalAccessException, InstantiationException {
        CSVObject csvObject = null;

        Object instanceClass = aClass.newInstance();

        for (Annotation declaredAnnotation : aClass.getDeclaredAnnotations()) {
            if (declaredAnnotation instanceof CSVData) {
                CSVData data = (CSVData) declaredAnnotation;

                List<CSVColumn> csvColumns = new ArrayList<>();

                for (Field field : aClass.getDeclaredFields()) {
                    for (Annotation annotation : field.getDeclaredAnnotations()) {
                        if (annotation instanceof Column) {
                            Column column = (Column) annotation;
                            Coordinate head = new Coordinate(column.head());
                            field.setAccessible(true);
                            Object defaultValue = field.get(instanceClass);
                            field.setAccessible(false);
                            CSVColumn csvColumn = new CSVColumn(field.getName(), getParser(field.getType()), head, column.size(), column.main(), defaultValue);
                            csvColumns.add(csvColumn);
                        }
                    }
                }
                csvObject = new CSVObject(data.url(), csvColumns, aClass);
            }
        }

        return csvObject;
    }

    @Override
    public void registerParser(Class c, ru.undframe.field.Field field) {
        this.fieldParsers.put(c, field);
    }

    private ru.undframe.field.Field getParser(Class c) {
        if (fieldParsers.containsKey(c))
            return fieldParsers.get(c);

        throw new IllegalArgumentException("Class " + c.getName() + " don`t support");
    }

}
