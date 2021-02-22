package ru.undframe.field;

@FieldParser(parseClasses = {Short.class,short.class})
public class ShortField implements Field<Short> {
    @Override
    public Short parse(String[] s) {
        short result = Short.parseShort(s[0]);

        for (int i = 1; i < s.length; i++) {
            String addValue = s[i];
            result += Short.parseShort(addValue);
        }

        return result;    }
}
