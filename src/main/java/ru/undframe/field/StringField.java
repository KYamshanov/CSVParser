package ru.undframe.field;

@FieldParser(parseClasses = {String.class})
public class StringField implements Field<String> {
    @Override
    public String parse(String[][] s) {
        return s[0][0];
    }
}
