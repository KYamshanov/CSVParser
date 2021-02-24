package ru.undframe.field;

@FieldParser(parseClasses = {Float.class,float.class})
public class FloatField implements PrimitiveField<Float> {

    @Override
    public Float parse(String s) {
        return Float.parseFloat(s);
    }
}
