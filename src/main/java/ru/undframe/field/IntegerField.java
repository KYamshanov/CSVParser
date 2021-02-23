package ru.undframe.field;

import java.util.Arrays;

@FieldParser(parseClasses = {Integer.class, int.class})
public class IntegerField implements Field<Integer> {

    @Override
    public Integer parse(String[][] s) {
        return Integer.parseInt(s[0][0]);
    }
}
