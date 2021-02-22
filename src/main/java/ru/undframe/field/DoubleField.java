package ru.undframe.field;

import java.util.List;

@FieldParser(parseClasses = {Double.class,double.class})
public class DoubleField implements Field<Double> {
    @Override
    public Double parse(String[] s) {

        double result = Double.parseDouble(s[0]);

        for (int i = 1; i < s.length; i++) {
            String addValue = s[i];
            result += Double.parseDouble(addValue);
        }

        return result;
    }
}
