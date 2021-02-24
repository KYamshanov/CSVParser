package ru.undframe.field;

@FieldParser(parseClasses = {String.class})
public class StringField implements PrimitiveField<String> {
    @Override
    public String parse(String s) {
        return s;
    }
}
