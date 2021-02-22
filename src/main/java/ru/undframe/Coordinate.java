package ru.undframe;

public class Coordinate {

    private int x =-1, y;
    public Coordinate(String coordinateName) {
        int skipCharacters = 0;
        for (char c : coordinateName.toCharArray()) {
            if ('A' <= c && 'Z' >= c) {
                x = c - 'A';
            } else break;
            skipCharacters++;
        }

        if(x<0)
            throw new IllegalArgumentException();
        y = Integer.parseInt(coordinateName.substring(skipCharacters));
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Coordinate add(int x, int y) {
        this.x += x;
        this.y += y;

        return this;
    }

    public Coordinate clone(){
        return new Coordinate(x, y);
    }

}
