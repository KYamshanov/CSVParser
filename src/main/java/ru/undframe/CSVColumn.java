package ru.undframe;

import ru.undframe.field.Field;

import java.util.Objects;

public class CSVColumn {

    private Coordinate head;
    private int usageColumn;
    private String name;
    private Field field;
    private boolean main;
    private Object defaultValue;

    public CSVColumn(String name,Field field,Coordinate head,int usageColumn,boolean main,Object defaultValue) {
        this.name = name;
        this.head = head;
        this.field = field;
        this.usageColumn = usageColumn;
        this.main = main;
        this.defaultValue = defaultValue;
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

    @Override
    public String toString() {
        return "CSVColumn{" +
                "head=" + head +
                ", name='" + name + '\'' +
                '}';
    }
}
