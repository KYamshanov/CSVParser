package ru.undframe;

import java.io.IOException;

public interface DataReader<T> {

    ArrayTable read(T t) throws IOException;

}
