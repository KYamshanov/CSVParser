package ru.undframe;

public class Coordinate implements Position {

    private int x,y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    @Override
    public int getYMax() {
        return y;
    }

    @Override
    public int getXMax() {
        return x;
    }

    @Override
    public Position clone() {
        return new Coordinate(x,y);
    }

    @Override
    public Position add(int x, int y) {

        this.x += x;
        this.y += y;

        return this;
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
}
