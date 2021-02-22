package ru.undframe.field;

public class StringField implements Field<String> {

    public static final StringField INSTANCE = new StringField();

    @Override
    public String parse(String s) {
        return s;
    }
}
