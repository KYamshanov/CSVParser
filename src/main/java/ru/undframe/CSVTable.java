package ru.undframe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSVTable<T> {

    private Object parameter;
    private List<CSVColumn> list;
    private ArrayTable data;
    private Class<T> instanceClass;
    private List<T> objects;
    private DataReader reader;

    public CSVTable(Object parameter, DataReader reader, List<CSVColumn> csvColumns, Class<T> instanceClass) throws IOException {

        Objects.requireNonNull(parameter);
        Objects.requireNonNull(reader);

        this.parameter = parameter;
        this.list = csvColumns;
        this.reader = reader;
        this.data = reader.read(this.parameter);
        this.instanceClass = instanceClass;
        this.objects = getValues();
    }

    public List<T> getObjects() {
        return objects;
    }

    public ArrayTable getData() {
        return data;
    }

    public Object getParameter() {
        return parameter;
    }

    void refreshData() throws IOException {
        this.data = reader.read(parameter);
        this.objects = getValues();
    }

    private List<T> getValues() {

        List<T> values = new ArrayList<>();

        try {
            int countObjects = Integer.MAX_VALUE;
            for (CSVColumn csvColumn : list) {
                ArrayTable fromTable = csvColumn.getFromTable() == null ? data : csvColumn.getFromTable();


                List<String> column = fromTable.getColumn(csvColumn.getHead().getX(), csvColumn.getHead());


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

                    ArrayTable fromTable = csvColumn.getFromTable() == null ? data : csvColumn.getFromTable();


                    List<String> dataValues = new ArrayList<>();

                    for (int x = 0; x < csvColumn.getUsageColumn(); x++) {


                        Position coordinate = csvColumn.isConstantPosition() ? csvColumn.getHead().clone() :
                                csvColumn.getHead().clone().add(x, id);
                        String value = fromTable.getValue(coordinate);
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
                            Position coordinate = Position.of(dataValues.get(0));


                            String[][] valuesLink = new String[coordinate.getDeltaY() + 1][coordinate.getDeltaX() + 1];

                            for (int y = coordinate.getY(), i = 0; y < coordinate.getYMax() + 1; y++, i++) {
                                String[] line = new String[coordinate.getDeltaX() + 1];
                                for (int x = coordinate.getX(), i1 = 0; x < coordinate.getXMax() + 1; x++, i1++) {
                                    line[i1] = fromTable.getValue(x, y - 1);
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
