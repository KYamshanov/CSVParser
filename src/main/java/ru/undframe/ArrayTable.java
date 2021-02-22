package ru.undframe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTable {

    private List<List<String>> data;
    private String separator;

    public ArrayTable(List<String> lines, String separator) {

        this.separator = separator;


        List<List<String>> xyObjects = new ArrayList<>();

        for (String line : lines) {
            xyObjects.add(Arrays.asList(
                    line.split(separator)));
        }

        int maxLengthColumn = 0;
        for (List<String> strings : xyObjects) {
            maxLengthColumn = Math.max(strings.size(), maxLengthColumn);
        }

        List<List<String>> rotateArray = new ArrayList<>();


        for (int i = 0; i < maxLengthColumn; i++) {
            List<String> newColumn = new ArrayList<>();
            for (List<String> strings : xyObjects) {
                if(i>=strings.size())
                    newColumn.add("");
                else{
                    newColumn.add(strings.get(i));
                }
            }
            rotateArray.add(newColumn);

        }
        data = rotateArray;

        xyObjects.clear();

    }

    public String getValue(int x, int y) {
        List<String> column;
        if (x >= data.size() || y >= (column = data.get(x)).size())
            return "";
        return column.get(y);
    }


    @Override
    public String toString() {
        return "ArrayTable{" +
                "data=" + data +
                ", separator='" + separator + '\'' +
                '}';
    }

    public String getValue(Coordinate coordinate) {
        return getValue(coordinate.getX(), coordinate.getY()-1);
    }

    public List<String> getColumn(int x,Coordinate head) {
        List<String> column = data.get(x);
        return column.subList(head.getY()-1,column.size());
    }
}
