package ru.undframe.field;

public class FloatField implements Field<Float> {

    public static final FloatField INSTANCE = new FloatField();

    @Override
    public Float parse(String s) {
        return Float.parseFloat(s);
    }
}
