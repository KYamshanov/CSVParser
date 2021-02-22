package ru.undframe.field;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface FieldParser {

    Class[] parseClasses();

}
