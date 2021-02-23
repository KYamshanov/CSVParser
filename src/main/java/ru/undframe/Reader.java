package ru.undframe;

import java.io.IOException;

public interface Reader<T> {

    ArrayTable read(T t) throws IOException;

}
