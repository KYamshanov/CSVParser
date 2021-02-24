package ru.undframe;

import ru.undframe.field.ComplexField;
import ru.undframe.field.PrimitiveField;
import ru.undframe.field.FieldParser;

@FieldParser(parseClasses = Matrix.class)
public class MatrixParser implements ComplexField<Matrix> {
    @Override
    public Matrix parse(CSVObject s) {

        return new Matrix(s.get(0, 0).getValue(), s.get(1, 0).getValue(),
                Integer.parseInt(s.get(0, 1).getValue()), Integer.parseInt(s.get(1, 1).getValue()));

    }
}
