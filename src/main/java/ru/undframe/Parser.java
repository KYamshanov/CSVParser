package ru.undframe;

import ru.undframe.field.PrimitiveField;

import java.io.IOException;

public interface Parser {
    void launch(String pac) throws IllegalAccessException, InstantiationException, IOException;

    void refreshCSVs() throws IOException;

    <T> Table<T> getCSVObject(Class<T> c);

    void registerParser(Class c, PrimitiveField field);
}
