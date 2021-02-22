package ru.undframe;

import ru.undframe.field.Field;
import ru.undframe.field.FieldParser;

public class MultiplayField2 implements Field<MultiplayObject> {
    @Override
    public MultiplayObject parse(String[] s) {
        MultiplayObject multiplayObject = new MultiplayObject(
                s[0], Integer.parseInt(s[1])*100
        );

        return multiplayObject;
    }
}
