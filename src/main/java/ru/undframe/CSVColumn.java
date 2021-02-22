package ru.undframe;

import ru.undframe.field.Field;

public class CSVColumn {

    private Coordinate head;
    private String name;
    private Field field;

    public CSVColumn(String name,Field field,Coordinate head) {
        this.name = name;
        this.head = head;
        this.field = field;
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

    @Override
    public String toString() {
        return "CSVColumn{" +
                "head=" + head +
                ", name='" + name + '\'' +
                '}';
    }
}
