package ru.undframe;

@CSVData(priority = 0)
@CSVGetterFromLink(link= "https://docs.google.com/spreadsheets/d/1JzYuQnrTfyuLtp1X8EJJzbViMSl6ZYUVeu5pyAQjibM/export?format=csv")
public class TestTable2 {

    @Column(head = "A2", main = true)
    private String id;

}
