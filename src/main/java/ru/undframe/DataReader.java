package ru.undframe;

import java.io.IOException;

public interface DataReader<T> {

    CSVTable read(T t) throws IOException;

}
