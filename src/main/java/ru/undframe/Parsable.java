package ru.undframe;

import ru.undframe.field.Field;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Parsable {

    Class<? extends Field> parser();

}
