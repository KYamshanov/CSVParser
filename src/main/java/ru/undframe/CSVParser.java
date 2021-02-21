package ru.undframe;

import org.reflections.Reflections;
import sun.reflect.Reflection;

import java.lang.annotation.Annotation;
import java.util.Set;

public class CSVParser implements Parser{




    public static void launch(String pac) {


        Reflections reflections = new Reflections(pac);

        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(CSVData.class);

        for (Class<?> aClass : typesAnnotatedWith) {
            System.out.println(aClass.toString());
        }

    }
}
