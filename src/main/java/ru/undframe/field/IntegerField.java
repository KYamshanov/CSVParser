package ru.undframe.field;

@FieldParser(parseClasses = {Integer.class, int.class})
public class IntegerField implements PrimitiveField<Integer> {

    @Override
    public Integer parse(String s) {
        return Integer.parseInt(s);
    }
}
