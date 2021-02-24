package ru.undframe.field;

@FieldParser(parseClasses = {Double.class,double.class})
public class DoubleField implements PrimitiveField<Double> {
    @Override
    public Double parse(String s) {

        return Double.parseDouble(s);
    }
}
