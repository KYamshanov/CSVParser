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
            List<Class<?>> fieldParsers = new ArrayList<>(reflections.getTypesAnnotatedWith(FieldParser.class));

            fieldParsers.sort((o1, o2) -> {
                FieldParser o1Annotation = o1.getAnnotation(FieldParser.class);
                FieldParser o2Annotation = o2.getAnnotation(FieldParser.class);
                return Integer.compare(o1Annotation.prority(), o2Annotation.prority());
            });

            for (Class<?> fieldParser : fieldParsers) {
                if (Arrays.asList(fieldParser.getInterfaces()).contains(ru.undframe.field.Field.class)) {

                    ru.undframe.field.Field<?> field = (ru.undframe.field.Field) fieldParser.newInstance();
                    FieldParser parses = fieldParser.getDeclaredAnnotation(FieldParser.class);
                    for (Class aClass : parses.parseClasses()) {
                        this.fieldParsers.put(aClass, field);
                    }

                }
            }

            List<Class<?>> typesAnnotatedWith = new ArrayList<>(reflections.getTypesAnnotatedWith(CSVData.class));

            typesAnnotatedWith.sort((o1, o2) -> {
                CSVData o1Annotation = o1.getAnnotation(CSVData.class);
                CSVData o2Annotation = o2.getAnnotation(CSVData.class);
                return Integer.compare(o1Annotation.prority(), o2Annotation.prority());
            });

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

    private static Parser parser;

    public static Parser getInstance() {
        if(parser==null)
            parser = new CSVParser();
        return parser;
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
                            Parsable parsable = field.getAnnotation(Parsable.class);
                            Coordinate head = new Coordinate(column.head());
                            field.setAccessible(true);
                            Object defaultValue = field.get(instanceClass);
                            field.setAccessible(false);
                            CSVColumn csvColumn = new CSVColumn(field.getName(), parsable != null ? parsable.parser().newInstance() : getParser(field.getType()), head, column.size(), column.main(), defaultValue);
                            csvColumns.add(csvColumn);
                        }
                    }
                }

                InitializationObject initMethod = null;
                if(instanceClass instanceof InitializationObject)
                    initMethod = (InitializationObject)instanceClass;

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
