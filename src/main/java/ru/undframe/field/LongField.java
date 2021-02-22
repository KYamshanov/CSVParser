package ru.undframe.field;

public class LongField implements Field<Long> {

    public static final LongField INSTANCE = new LongField();

    @Override
    public Long parse(String s) {
        return Long.parseLong(s);
    }
}
