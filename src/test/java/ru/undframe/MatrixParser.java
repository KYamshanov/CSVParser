package ru.undframe;

import ru.undframe.field.Field;
import ru.undframe.field.FieldParser;

import java.util.Arrays;

@FieldParser(parseClasses = Matrix.class)
public class MatrixParser implements Field<Matrix> {
    @Override
    public Matrix parse(String[][] s) {
        return new Matrix(s[0][0],s[1][0],Integer.parseInt(s[0][1]),Integer.parseInt(s[1][1]));
    }
}
