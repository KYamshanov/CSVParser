package ru.undframe;

import ru.undframe.field.Field;

public class CSVColumn {

    private Position head;
    private String name;
    private Field field;
    private boolean main;
    private Object defaultValue;
    private boolean linkField;
    private boolean constantPosition;

    private ArrayTable fromTable= null;

    public CSVColumn(String name, Field field,
                     Position head,
                     boolean main, Object defaultValue,
                     boolean linkField,
                     boolean constantPosition) {
        this.name = name;
        this.head = head;
        this.field = field;
        this.main = main;
        this.defaultValue = defaultValue;
        this.linkField = linkField;
        this.constantPosition = constantPosition;
    }


    public boolean isConstantPosition() {
        return constantPosition;
    }

    public Position getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

    public ArrayTable getFromTable() {
        return fromTable;
    }

    public void setFromTable(ArrayTable fromTable) {
        this.fromTable = fromTable;
    }

    public int getUsageColumn() {
        return head.getDeltaX()+1;
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
                "head=" + head +
                ", name='" + name + '\'' +
                '}';
    }

}
