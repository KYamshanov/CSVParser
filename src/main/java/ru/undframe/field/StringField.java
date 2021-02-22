package ru.undframe.field;

@FieldParser(parseClasses = {String.class})
public class StringField implements Field<String> {
    @Override
    public String parse(String[] s) {
        StringBuilder result = new StringBuilder(s[0]);

        for (int i = 1; i < s.length; i++) {
            String addValue = s[i];
            result.append(addValue);
        }

        return result.toString();
    }
}
