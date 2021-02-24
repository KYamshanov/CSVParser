package ru.undframe;

public class CSVObject {


    private String[][] values;
    private Position position;

    public CSVObject(CSVTable table ,Position position) {
        this.values = position.getValuesFromTable(table);
        this.position = position;
    }

    public String[][] getValues() {
        return values;
    }


}
