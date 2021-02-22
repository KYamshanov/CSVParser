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

    public CSVObject(String url, List<CSVColumn> csvColumns, Class<T> instanceClass) {
        this.url = url;
        this.list = csvColumns;
        this.instanceClass = instanceClass;

        this.data = getData();
        this.objects = getValues();
    }

    public List<T> getObjects() {
        return objects;
    }

    void refreshData() {
        this.data = getData();
        this.objects = getValues();
    }

    private List<T> getValues() {

        List<T> values = new ArrayList<>();

        try {
            int countObjects = Integer.MAX_VALUE;
            for (CSVColumn csvColumn : list) {

                int columnItems = data.getColumn(csvColumn.getHead().getX(), csvColumn.getHead()).size();

                if (csvColumn.isMain()) {
                    countObjects = columnItems;
                    break;
                }

                countObjects = Math.max(countObjects, columnItems);
            }

            for (int id = 0; id < countObjects; id++) {
                T t = instanceClass.newInstance();
                for (CSVColumn csvColumn : list) {

                    List<String> dataValues = new ArrayList<>();

                    for (int x = 0; x < csvColumn.getUsageColumn(); x++) {
                        Coordinate coordinate = csvColumn.getHead().clone().add(x, id);
                        String value = this.data.getValue(coordinate);
                        if (!value.isEmpty())
                            dataValues.add(value);
                    }


                    Field f = t.getClass().getDeclaredField(csvColumn.getName());
                    f.setAccessible(true);
                    f.set(t, dataValues.size()==0 ? csvColumn.getDefaultValue() :csvColumn.getField().parse(dataValues.toArray(new String[]{})));
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
