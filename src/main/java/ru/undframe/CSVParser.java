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
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(CSVData.class);
        for (Class<?> aClass : typesAnnotatedWith) {
            for (Annotation declaredAnnotation : aClass.getDeclaredAnnotations()) {
                if (declaredAnnotation instanceof CSVData) {
                    CSVObject csvObject = instanceCSVObject(aClass);
                    registerCSV(aClass, csvObject);
                }
            }
        }
    }


    public static Parser getInstance(){
        return new CSVParser();
    }

    void registerCSV(Class c, CSVObject csv) {
        csvObjects.put(c, csv);
    }

    @Override
    public void refreshCSVs(){
        for (CSVObject csv : csvObjects.values()) {
            csv.refreshData();
        }
    }

    @Override
    public CSVObject getCSVObject(Class c) {
        return csvObjects.get(c);
    }

    private CSVObject instanceCSVObject(Class aClass) {
        CSVObject csvObject = null;

        for (Annotation declaredAnnotation : aClass.getDeclaredAnnotations()) {
            if (declaredAnnotation instanceof CSVData) {
                CSVData data = (CSVData) declaredAnnotation;

                List<CSVColumn> csvColumns = new ArrayList<>();

                for (Field field : aClass.getDeclaredFields()) {
                    for (Annotation annotation : field.getDeclaredAnnotations()) {
                        if (annotation instanceof Column) {
                            Column column = (Column) annotation;
                            Coordinate head = new Coordinate(column.head());
                            CSVColumn csvColumn = new CSVColumn(field.getName(), getParser(field.getType()), head);
                            csvColumns.add(csvColumn);
                        }
                    }
                }
                csvObject = new CSVObject(data.url(),csvColumns, aClass);
            }
        }

        return csvObject;
    }

    @Override
    public void registerParser(Class c, ru.undframe.field.Field field){
        this.fieldParsers.put(c, field);
    }

    private ru.undframe.field.Field getParser(Class c) {
        if (c.equals(String.class))
            return StringField.INSTANCE;
        else if (c.equals(Integer.class) || c.equals(int.class))
            return IntegerField.INSTANCE;
        else if (c.equals(Long.class) || c.equals(long.class))
            return LongField.INSTANCE;
        else if (c.equals(Double.class) || c.equals(double.class))
            return DoubleField.INSTANCE;
        else if (c.equals(Float.class) || c.equals(float.class))
            return FloatField.INSTANCE;
        else if (c.equals(Short.class) || c.equals(short.class))
            return ShortField.INSTANCE;

        if(fieldParsers.containsKey(c))
            return fieldParsers.get(c);

        throw new IllegalArgumentException();
    }

}
