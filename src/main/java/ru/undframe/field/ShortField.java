package ru.undframe.field;

@FieldParser(parseClasses = {Short.class, short.class})
public class ShortField implements PrimitiveField<Short> {
    @Override
    public Short parse(String s) {
        return Short.parseShort(s);
    }
}
