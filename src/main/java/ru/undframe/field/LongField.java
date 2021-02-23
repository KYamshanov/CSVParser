package ru.undframe.field;

@FieldParser(parseClasses = {Long.class,long.class})
public class LongField implements Field<Long> {
    @Override
    public Long parse(String[][] s) {
        return Long.parseLong(s[0][0]);
    }
}
