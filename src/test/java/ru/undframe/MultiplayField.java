package ru.undframe;

import ru.undframe.field.Field;
import ru.undframe.field.FieldParser;

import java.util.Arrays;

@FieldParser(parseClasses = MultiplayObject.class)
public class MultiplayField implements Field<MultiplayObject> {
    @Override
    public MultiplayObject parse(String[] s) {
        MultiplayObject multiplayObject = new MultiplayObject(
                s[0], Integer.parseInt(s[1])
        );

        return multiplayObject;
    }
}
