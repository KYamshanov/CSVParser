package ru.undframe;

import java.util.Objects;

public class Cell {

    private String value;

    private Cell(String value) {
        this.value = value;
    }


    public static Cell of(String s){
        return new Cell(s);
    }

    public static Cell[][] of(String[][] string){

        Cell[][] cells = new Cell[string.length][string[0].length];

        for (int x = 0; x < string.length; x++) {
            for (int y = 0; y < string[x].length; y++) {
                cells[x][y] = new Cell(string[x][y]);
            }
        }
        return cells;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return Objects.equals(value, cell.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }


    @Override
    public String toString() {
        return value;
    }
}
