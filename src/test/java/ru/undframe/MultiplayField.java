package ru.undframe;

import ru.undframe.field.ComplexField;
import ru.undframe.field.PrimitiveField;
import ru.undframe.field.FieldParser;

@FieldParser(parseClasses = MultiplayObject.class)
public class MultiplayField implements ComplexField<MultiplayObject> {
    @Override
    public MultiplayObject parse(CSVObject s) {
        MultiplayObject multiplayObject = new MultiplayObject(
                s.get(0,0).getValue(), Integer.parseInt(s.get(0,1).getValue())
        );

        return multiplayObject;
    }
}
