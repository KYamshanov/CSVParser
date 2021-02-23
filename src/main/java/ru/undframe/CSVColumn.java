package ru.undframe;

import ru.undframe.field.Field;

public class CSVColumn {

    private Coordinate head;
    private int usageColumn;
    private String name;
    private Field field;
    private boolean main;
    private Object defaultValue;
    private boolean linkField;


    public CSVColumn(String name,Field field,
                     Coordinate head,int usageColumn,
                     boolean main,Object defaultValue,
                     boolean linkField) {
        this.name = name;
        this.head = head;
        this.field = field;
        this.usageColumn = usageColumn;
        this.main = main;
        this.defaultValue = defaultValue;
        this.linkField = linkField;
    }


    public Coordinate getHead() {
        return head;
    }

    public String getName() {
        return name;
    }

    public Field getField() {
        return field;
    }

    public int getUsageColumn() {
        return usageColumn;
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
