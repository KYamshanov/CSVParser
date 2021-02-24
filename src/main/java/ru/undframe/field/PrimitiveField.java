package ru.undframe.field;

public interface PrimitiveField<T> extends Field {

    T parse(String s);

}
