package ru.undframe;

public class Coordinate {

    private int xMin, yMin;
    private int xMax,yMax;

    public Coordinate(int x, int y) {
        this.xMax = x;
        this.xMin = x;
        this.yMax = y;
        this.yMin = y;
    }

    private Coordinate(int xMin,int xMax,int yMin,int yMax){
        this.xMax = Math.max(xMax, xMin);
        this.xMin = Math.min(xMax, xMin);

        this.yMax = Math.max(yMax, yMin);
        this.yMin = Math.min(yMax, yMin);

    }

    public static Coordinate of(String coordinate) {
        String[] split = coordinate.split(":");

        if(split.length==2){
            Coordinate minCoordinate = getCoordinateWithName(split[0]);
            Coordinate maxCoordinate = getCoordinateWithName(split[1]);
            Coordinate coordinate1 = new Coordinate(minCoordinate.getX(), maxCoordinate.getX(), minCoordinate.getY(), maxCoordinate.getY());
            return coordinate1;
        }

        return getCoordinateWithName(coordinate);
    }

    private static Coordinate getCoordinateWithName(String columnName) {
        int x = 0;
        int y;
        int skipCharacters = 0;
        int discharge = 'Z' + 1 - 'A';
        for (char c : columnName.toCharArray()) {
            if ('A' <= c && 'Z' >= c) {

                if (skipCharacters > 0) {
                    if (x == 0) x = discharge;
                    else x *= discharge;
                }

                x += c - 'A';

            } else break;
            skipCharacters++;
        }

        if (x < 0)
            throw new IllegalArgumentException();
        y = Integer.parseInt(columnName.substring(skipCharacters));
        return new Coordinate(x, y);
    }


    public int getX() {
        return xMin;
    }

    public int getY() {
        return yMin;
    }

    public int getXMin() {
        return xMin;
    }

    public int getYMin() {
        return yMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public boolean isCoorpinatePlane(){
        return xMax != xMin || yMin != yMax;
    }


    public Coordinate add(int x, int y) {
        this.xMin += x;
        this.yMin += y;

        return this;
    }

    public int deltaX(){
        return xMax - xMin;
    }

    public int deltaY(){
        return yMax - yMin;
    }

    public Coordinate addMax(int xMax, int yMax) {
        this.xMax += xMax;
        this.yMax += yMax;

        return this;
    }



    public Coordinate clone() {
        return new Coordinate(xMin, xMax, yMin, yMax);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "xMin=" + xMin +
                ", yMin=" + yMin +
                ", xMax=" + xMax +
                ", yMax=" + yMax +
                '}';
    }
}
