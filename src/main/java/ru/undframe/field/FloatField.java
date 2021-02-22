package ru.undframe.field;

@FieldParser(parseClasses = {Float.class,float.class})
public class FloatField implements Field<Float> {

    @Override
    public Float parse(String[] s) {
        float result = Float.parseFloat(s[0]);

        for (int i = 1; i < s.length; i++) {
            String addValue = s[i];
            result += Float.parseFloat(addValue);
        }

        return result;
    }
}
