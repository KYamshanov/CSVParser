package ru.undframe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CSVObject<T> {

    private Object redableCSV;
    private List<CSVColumn> list;
    private ArrayTable data;
    private Class<T> instanceClass;
    private List<T> objects;

    public CSVObject(Object redableCSV, ArrayTable table, List<CSVColumn> csvColumns, Class<T> instanceClass) {
        this.redableCSV = redableCSV;
        this.list = csvColumns;
        this.data = table;
        this.instanceClass = instanceClass;
        this.objects = getValues();
    }

    public List<T> getObjects() {
        return objects;
    }

    public Object getRedableCSV() {
        return redableCSV;
    }

    void refreshData(ArrayTable table) {
        this.data = table;
        this.objects = getValues();
    }

    private List<T> getValues() {

        List<T> values = new ArrayList<>();

        try {
            int countObjects = Integer.MAX_VALUE;
            for (CSVColumn csvColumn : list) {
                List<String> column = data.getColumn(csvColumn.getHead().getX(), csvColumn.getHead());


                int columnItems;

                for (columnItems = 0; columnItems < column.size(); columnItems++) {
                    if (column.get(columnItems).isEmpty()) break;
                }
                if (csvColumn.isMain()) {
                    countObjects = columnItems;
                    break;
                }
                countObjects = Math.min(countObjects, columnItems);
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


                    Object result = null;


                    if (dataValues.size() == 0)
                        result = csvColumn.getDefaultValue();
                    else {
                        if (!csvColumn.isLinkField()) {
                            result = csvColumn.getField().parse(new String[][]{dataValues.toArray(new String[]{})});
                        } else {
                            Coordinate coordinate = Coordinate.of(dataValues.get(0));


                            String[][] valuesLink = new String[coordinate.deltaY() + 1][coordinate.deltaX() + 1];

                            for (int y = coordinate.getY(), i = 0; y < coordinate.getYMax() + 1; y++, i++) {
                                String[] line = new String[coordinate.deltaX() + 1];
                                for (int x = coordinate.getX(), i1 = 0; x < coordinate.getXMax() + 1; x++, i1++) {
                                    line[i1] = data.getValue(x, y-1);
                                }
                                valuesLink[i] = line;
                            }
                            result = csvColumn.getField().parse(valuesLink);


                        }
                    }

                    Field f = t.getClass().getDeclaredField(csvColumn.getName());
                    f.setAccessible(true);
                    f.set(t, result);
                    f.setAccessible(false);
                }

                if (t instanceof InitializationObject)
                    ((InitializationObject) t).init();

                values.add(t);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return values;
    }
}
