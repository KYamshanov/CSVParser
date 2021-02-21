package ru.undframe;

@CSVData
public class TestTable {


    @Column(head= "A1")
    private String name;
    @Column(head = "B2")
    private int age;

}
