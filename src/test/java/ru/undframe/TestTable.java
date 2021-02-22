package ru.undframe;

import java.util.Objects;

@CSVData(url = "https://docs.google.com/spreadsheets/d/1Pg6hx6uf_eBjoMy4olOVbUU13Z1XkeOAV0VYoxJnpL4/export?format=csv")
public class TestTable {


    @Column(head= "A1",main = true)
    private String name;
    @Column(head = "B1")
    private String age;
    @Column(head = "C1")
    private int size = -1;

    @Column(head = "E2", size = 2)
    private MultiplayObject multiplayObject;

    public TestTable(String name, String age, int size, MultiplayObject multiplayObject) {
        this.name = name;
        this.age = age;
        this.size = size;
        this.multiplayObject = multiplayObject;
    }

    public TestTable() {
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestTable testTable = (TestTable) o;
        return size == testTable.size &&
                Objects.equals(name, testTable.name) &&
                Objects.equals(age, testTable.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, size);
    }

    @Override
    public String toString() {
        return "TestTable{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", size=" + size +
                '}';
    }
}
