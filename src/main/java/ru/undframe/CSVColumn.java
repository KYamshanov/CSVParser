package ru.undframe;

import ru.undframe.field.ComplexField;
import ru.undframe.field.Field;
import ru.undframe.field.PrimitiveField;

public class CSVColumn {

    private Position position;
    private String name;
    private Field field;
    private boolean main;
    private Object defaultValue;
    private boolean linkField;
    private boolean constantPosition;

    private CSVTable fromTable= null;

    public CSVColumn(String name, Field field,
                     Position position,
                     boolean main, Object defaultValue,
                     boolean linkField,
                     boolean constantPosition) {


        this.name = name;
        this.position = position;
        this.field = field;
        this.main = main;
        this.defaultValue = defaultValue;
        this.linkField = linkField;
        this.constantPosition = constantPosition;
    }


    public boolean isConstantPosition() {
        return constantPosition;
    }

    public Position getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

    public CSVTable getFromTable() {
        return fromTable;
    }

    public void setFromTable(CSVTable fromTable) {
        this.fromTable = fromTable;
    }

    public int getUsageColumn() {
        return position.getDeltaX()+1;
    }

    public boolean isMain() {
        return main;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public boolean isLinkField() {
        return linkField;
    }

    @Override
    public String toString() {
        return "CSVColumn{" +
                "position=" + position +
                ", name='" + name + '\'' +
                '}';
    }

    public Object parse(CSVObject csvObject) {

        if(field instanceof PrimitiveField)
            return ((PrimitiveField) field).parse(csvObject.getFirts().getValue());
        else if(field instanceof ComplexField){
            return  ((ComplexField) field).parse(csvObject);
        }

        throw new IllegalArgumentException("field " + field.getClass().getName() + " don`t parse " + csvObject.toString());
    }
}
