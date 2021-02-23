package ru.undframe.field;

import java.util.List;

@FieldParser(parseClasses = {Double.class,double.class})
public class DoubleField implements Field<Double> {
    @Override
    public Double parse(String[][] s) {

        return Double.parseDouble(s[0][0]);
    }
}
