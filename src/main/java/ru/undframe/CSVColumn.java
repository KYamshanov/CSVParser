package ru.undframe;

import java.util.List;

public class CSVColumn {

    private String head;
    private List<String> values;

    public CSVColumn(String head, List<String> values) {
        this.head = head;
        this.values = values;
    }

    public String getHead() {
        return head;
    }

    public List<String> getValues() {
        return values;
    }
}
