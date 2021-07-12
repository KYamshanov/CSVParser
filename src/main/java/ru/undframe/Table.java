package ru.undframe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table<T> {

    private Object parameter;
    private List<CSVColumn> list;
    private CSVTable data;
    private Class<T> instanceClass;
    private List<T> objects;
    private DataReader reader;

    public Table(Object parameter, DataReader reader, List<CSVColumn> csvColumns, Class<T> instanceClass) throws IOException {

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

    public CSVTable getData() {
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
                CSVTable fromTable = csvColumn.getFromTable() == null ? data : csvColumn.getFromTable();


                List<String> column = fromTable.getColumn(csvColumn.getPosition().getX(), csvColumn.getPosition());


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
                    CSVTable fromTable = csvColumn.getFromTable() == null ? data : csvColumn.getFromTable();


                    Position position = csvColumn.getPosition();
                    if (!csvColumn.isConstantPosition())
                        position = position.addRelatively(0, id);
                    CSVObject csvObject = new CSVObject(fromTable, position);
                    Object result;
                    if (csvObject.isEmpty())
                        result = csvColumn.getDefaultValue();
                    else {
                        if (!csvColumn.isLinkField()) {
                            result = csvColumn.parse(csvObject);
                        } else {
                            Position coordinate = Position.of(position,csvObject.getFirts().getValue());
                            CSVObject csvObjectByLink = new CSVObject(getData(), coordinate);
                            result = csvColumn.parse(csvObjectByLink);
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
