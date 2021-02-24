package ru.undframe;

public class Coordinate implements Position {

    private int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    @Override
    public Position clone() {
        return new Coordinate(x, y);
    }

    @Override
    public Position add(int x, int y) {

        this.x += x;
        this.y += y;

        return this;
    }

    @Override
    public String[][] getValuesFromTable(CSVTable table) {
        return new String[][]{new String[]{
                table.getValue(getX(), getY())
        }};
    }

    @Override
    public Position addRelatively(int x, int y) {
        return new Coordinate(this.x+x,this.y+y);
    }

    public int getY() {
        return y;
    }

    @Override
    public int getDeltaX() {
        return 0;
    }

    @Override
    public int getDeltaY() {
        return 0;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
