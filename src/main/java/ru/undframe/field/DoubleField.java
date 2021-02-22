package ru.undframe.field;

public class DoubleField implements Field<Double> {

    public static final DoubleField INSTANCE = new DoubleField();

    @Override
    public Double parse(String s) {
        return Double.parseDouble(s);
    }
}
