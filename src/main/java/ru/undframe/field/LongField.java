package ru.undframe.field;

@FieldParser(parseClasses = {Long.class,long.class})
public class LongField implements Field<Long> {
    @Override
    public Long parse(String[] s) {
        long result = Long.parseLong(s[0]);

        for (int i = 1; i < s.length; i++) {
            String addValue = s[i];
            result += Long.parseLong(addValue);
        }

        return result;
    }
}
