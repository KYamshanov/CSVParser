package ru.undframe;

public class Diapason implements Position{

    private int xMin, yMin;
    private int xMax,yMax;

    public Diapason(int xMin, int xMax, int yMin, int yMax){
        this.xMax = Math.max(xMax, xMin);
        this.xMin = Math.min(xMax, xMin);

        this.yMax = Math.max(yMax, yMin);
        this.yMin = Math.min(yMax, yMin);

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


    public Diapason add(int x, int y) {
        this.xMin += x;
        this.yMin += y;

        return this;
    }

    @Override
    public String[][] getValuesFromTable(CSVTable table) {
        String[][] values = new String[getDeltaY() + 1][getDeltaX() + 1];
        for (int x = xMin,xV = 0; x <= xMax; x++,xV++) {
            for (int y = yMin,yV=0; y <= yMax; y++,yV++) {
                values[yV][xV] = table.getValue(x, y - 1);
            }
        }
        return values;
    }

    @Override
    public int getDeltaX(){
        return xMax - xMin;
    }

    public int getDeltaY(){
        return yMax - yMin;
    }

    public Diapason addMax(int xMax, int yMax) {
        this.xMax += xMax;
        this.yMax += yMax;

        return this;
    }



    public Diapason clone() {
        return new Diapason(xMin, xMax, yMin, yMax);
    }

    @Override
    public String toString() {
        return "Diapason{" +
                "xMin=" + xMin +
                ", yMin=" + yMin +
                ", xMax=" + xMax +
                ", yMax=" + yMax +
                '}';
    }
}
