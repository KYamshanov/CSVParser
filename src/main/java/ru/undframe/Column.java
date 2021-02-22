package ru.undframe;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    String head() default  "A1";
    int size() default 1;
    boolean main() default false;
}
