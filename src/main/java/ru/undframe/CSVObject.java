package ru.undframe;

public class CSVObject {


    private Cell[][] cells;
    private int values = 0;
    private Position position;

    public CSVObject(CSVTable table ,Position position) {
        this.cells = Cell.of(position.getValuesFromTable(table));
        this.position = position;

        for (Cell[] cellArray : cells)
            for (Cell cell : cellArray)
                values += cell.getValue().isEmpty()?0:1;

    }

    public Cell[][] getCells() {
        return cells;
    }


    public Cell get(int y,int x){
        return cells[y][x];
    }

    public boolean isCell(){
        return cells.length == 1 && cells[0].length == 1;
    }

    public boolean isEmpty(){
        return values==0;
    }

    public Cell getFirts(){
        return cells[0][0];
    }

}
