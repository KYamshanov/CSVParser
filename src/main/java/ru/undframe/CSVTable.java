package ru.undframe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVTable {

    private List<List<String>> data;
    private char separator;

    public CSVTable(List<String> lines, char separator) {

        this.separator = separator;


        List<List<String>> xyObjects = new ArrayList<>();

        for (String line : lines) {
            xyObjects.add(Arrays.asList(
                    separateLine(line,separator)));
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
        return "CSVTable{" +
                "data=" + data +
                ", separator='" + separator + '\'' +
                '}';
    }

    public String getValue(Position coordinate) {
        return getValue(coordinate.getX(), coordinate.getY()-1);
    }

    public List<String> getColumn(int x, Position head) {
        List<String> column = data.get(x);
        return column.subList(head.getY(),column.size());
    }


    public String[] separateLine(String line, char separateChar) {

        List<String> words = new ArrayList<>();

        char[] charArray = line.toCharArray();

        int beginIndex = 0;

        boolean stringMode = false;

        for (int index = 0; index < charArray.length; index++) {

            char symbol = charArray[index];

            if(symbol=='\"'){
                stringMode = !stringMode;
            }


            if(!stringMode)
                if (symbol == separateChar) {

                    if (beginIndex == index) {
                        words.add("");
                    } else {
                        words.add(line.substring(beginIndex, index));
                    }
                    beginIndex = index + 1;
                }

        }

        String substring = line.substring(beginIndex, charArray.length);
        if(substring.startsWith("\"") && substring.endsWith("\""))
            substring = substring.substring(1, substring.length() - 1);
        substring = substring.replaceAll("\"\"", "\"");
        words.add(substring);

        return words.toArray(new String[]{});

    }


}
