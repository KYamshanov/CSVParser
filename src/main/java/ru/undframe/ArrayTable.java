package ru.undframe;

import java.util.ArrayList;
import java.util.List;

public class ArrayTable {

    private List<List<String>> data = new ArrayList<>();
    private String separator;

    public ArrayTable(List<String> lines, String separator) {

        this.separator = separator;

        for (int y = 0; y < lines.size(); y++) {
            String[] objects = lines.get(y).split(separator);
            for (int x = 0; x < objects.length; x++) {
                List<String> tableColumn = x >= data.size() ? new ArrayList<>() : data.get(x);
                if (y >= tableColumn.size())
                    tableColumn.add(y, objects[x]);
                else
                    tableColumn.set(y, objects[x]);

                if (x >= data.size())
                    data.add(x, tableColumn);
                else
                    data.set(x, tableColumn);
            }
        }
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
