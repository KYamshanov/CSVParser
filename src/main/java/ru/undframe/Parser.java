package ru.undframe;

import java.io.IOException;

public interface Parser {
    void launch(String pac) throws IllegalAccessException, InstantiationException, IOException;

    void refreshCSVs() throws IOException;

    <T> CSVTable<T> getCSVObject(Class<T> c);

    void registerParser(Class c, ru.undframe.field.Field field);
}
