package ru.undframe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVObject<T> {

    private String url;
    private List<CSVColumn> list;
    private ArrayTable data;
    private Class<T> instanceClass;
    private List<T> objects;

    public CSVObject(String url,List<CSVColumn> csvColumns, Class<T> instanceClass) {
        this.url = url;
        this.list = csvColumns;
        this.instanceClass = instanceClass;

        this.data = getData();
        this.objects = getValues();
    }

    public List<T> getObjects() {
        return objects;
    }

    void refreshData(){
        this.data = getData();
        this.objects = getValues();
    }

    private List<T> getValues() {

        List<T> values = new ArrayList<>();

        try {
            int countObjects = 0;
            for (CSVColumn csvColumn : list) {
                countObjects = Math.max(countObjects, data.getColumn(csvColumn.getHead().getX(), csvColumn.getHead()).size());
            }

            for (int id = 0; id < countObjects; id++) {
                T t = instanceClass.newInstance();
                for (CSVColumn csvColumn : list) {
                    Coordinate coordinate = csvColumn.getHead().clone().add(0, id);
                    Field f = t.getClass().getDeclaredField(csvColumn.getName());
                    f.setAccessible(true);
                    f.set(t, csvColumn.getField().parse(data.getValue(coordinate)));
                    f.setAccessible(false);
                }
                values.add(t);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return values;
    }

    private ArrayTable getData() {
        try {
            URL url = new URL(this.url);
            Scanner s = new Scanner(url.openStream());
            List<String> lines = new ArrayList<>();
            while (s.hasNext())
                lines.add(s.nextLine());

            return new ArrayTable(lines, ",");

        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException();
    }

}
