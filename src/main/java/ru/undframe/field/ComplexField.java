package ru.undframe.field;

import ru.undframe.CSVObject;

public interface ComplexField<T> extends Field{

    T parse(CSVObject csvObject);

}
