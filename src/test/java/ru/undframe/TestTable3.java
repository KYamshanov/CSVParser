package ru.undframe;

import lombok.Getter;
import ru.undframe.csvreader.FileCSVReader;

import java.util.Objects;

@CSVData
@CSVGetterFromLink(link = "src/test/resources/TTable.csv")
@CSVDataReader(reader = FileCSVReader.class)
@Getter
public class TestTable3 {

    @Column(head = "A2",main = true)
    private String id;

    @Column(head = "B2")
    private String name;

    @Column(head = "C2")
    private long income;

    @Column(head = "D2")
    private long costs;

    @Column(head = "E2")
    private long remainder;

    public TestTable3(){}




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestTable3 that = (TestTable3) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
