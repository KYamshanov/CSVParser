package ru.undframe;

public interface Position {


    static Position of(String coordinate) {
        String[] split = coordinate.split(":");

        if(split.length==2){
            Coordinate minCoordinate = getCoordinateWithName(split[0]);
            Coordinate maxCoordinate = getCoordinateWithName(split[1]);
            Diapason diapason = new Diapason(minCoordinate.getX(), maxCoordinate.getX(), minCoordinate.getY(), maxCoordinate.getY());
            return diapason;
        }

        return getCoordinateWithName(coordinate);
    }

    static Coordinate getCoordinateWithName(String columnName) {
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


    int getDeltaX();

    int getDeltaY();

    int getY();

    int getX();


    Position clone();

    Position add(int x, int y);

    String[][] getValuesFromTable(CSVTable table);
}
