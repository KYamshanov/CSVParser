package ru.undframe;

import ru.undframe.field.ComplexField;
import ru.undframe.field.PrimitiveField;

public class MultiplayField2 implements ComplexField<MultiplayObject> {
    @Override
    public MultiplayObject parse(CSVObject s) {
        MultiplayObject multiplayObject = new MultiplayObject(
                s.get(0,0).getValue(), Integer.parseInt(s.get(0,1).getValue())*100
        );

        return multiplayObject;
    }
}
