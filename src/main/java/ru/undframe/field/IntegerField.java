package ru.undframe.field;

import java.util.Arrays;

@FieldParser(parseClasses = {Integer.class, int.class})
public class IntegerField implements Field<Integer> {

    @Override
    public Integer parse(String[] s) {
        int result = Integer.parseInt(s[0]);

        for (int i = 1; i < s.length; i++) {
            String addValue = s[i];
            result += Integer.parseInt(addValue);
        }

        return result;
    }
}
