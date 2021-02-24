package ru.undframe;

import java.util.Objects;

@CSVData()
@CSVGetterFromLink(link= "https://docs.google.com/spreadsheets/d/1Pg6hx6uf_eBjoMy4olOVbUU13Z1XkeOAV0VYoxJnpL4/export?format=csv")
public class TestTable {


    @Column(head = "A1", main = true)
    private String name;
    @Column(head = "B1")
    private String age;
    @Column(head = "C1")
    private int size = -1;

    @Column(head = "E2:F2")
    @Parsable(parser = MultiplayField2.class)
    private MultiplayObject multiplayObject;

    @Column(head = "AJ1")
    private String hash;

    @Column(head = "G2", link = true)
    private long leatherHat;
    @Column(head = "I2", link = true)
    private Matrix data;

    @Column(head = "H3",constantPosition = true)
    @DataLoader(fromCSV = TestTable2.class)
    private int count;

    public TestTable(String name, String age, int size, MultiplayObject multiplayObject, String hash, long leatherHat, Matrix matrix,int count) {
        this.name = name;
        this.age = age;
        this.size = size;
        this.multiplayObject = multiplayObject;
        this.hash = hash;
        this.leatherHat = leatherHat;
        this.data = matrix;
        this.count = count;
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
    public String toString() {
        return "TestTable{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", size=" + size +
                ", multiplayObject=" + multiplayObject +
                ", hash='" + hash + '\'' +
                ", leatherHat=" + leatherHat +
                ", data=" + data +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestTable testTable = (TestTable) o;
        return size == testTable.size &&
                leatherHat == testTable.leatherHat &&
                count == testTable.count &&
                Objects.equals(name, testTable.name) &&
                Objects.equals(age, testTable.age) &&
                Objects.equals(multiplayObject, testTable.multiplayObject) &&
                Objects.equals(hash, testTable.hash) &&
                Objects.equals(data, testTable.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, size, multiplayObject, hash, leatherHat, data, count);
    }
}
