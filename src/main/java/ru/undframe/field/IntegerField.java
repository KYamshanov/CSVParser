package ru.undframe.field;

public class IntegerField implements Field<Integer> {

    public static final IntegerField INSTANCE = new IntegerField();

    @Override
    public Integer parse(String s) {
        return Integer.parseInt(s);
    }
}
