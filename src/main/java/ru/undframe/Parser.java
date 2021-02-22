package ru.undframe;

public interface Parser {
    void launch(String pac);

    void refreshCSVs();

    <T> CSVObject<T> getCSVObject(Class<T> c);

    void registerParser(Class c, ru.undframe.field.Field field);
}
