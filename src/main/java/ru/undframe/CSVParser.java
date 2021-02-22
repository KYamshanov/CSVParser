package ru.undframe;

import org.reflections.Reflections;
import sun.reflect.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.util.Set;

public class CSVParser implements Parser{




    public static void launch(String pac) {


        Reflections reflections = new Reflections(pac);

        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(CSVData.class);

        for (Class<?> aClass : typesAnnotatedWith) {
            System.out.println(aClass.toString());
            aClass.ann
            for (AnnotatedType annotatedInterface : aClass.getAnnotatedInterfaces()) {
                System.out.println("dasdas "+annotatedInterface);
            }
            for (Annotation annotation : aClass.getAnnotations()) {
                System.out.println("dsds "+annotation.toString());
            }
        }

    }
}
