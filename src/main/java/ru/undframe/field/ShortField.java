package ru.undframe.field;

public class ShortField implements Field<Short> {

    public static final ShortField INSTANCE = new ShortField();

    @Override
    public Short parse(String s) {
        return Short.parseShort(s);
    }
}
