package ru.undframe;

@CSVData(url = "https://docs.google.com/spreadsheets/d/1JzYuQnrTfyuLtp1X8EJJzbViMSl6ZYUVeu5pyAQjibM/export?format=csv", priority = 0)
public class TestTable2 {

    @Column(head = "A2", main = true)
    private String id;

}
