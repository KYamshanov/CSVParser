package ru.undframe;


import lombok.Getter;

@Getter
@CSVData(priority = 5)
@CSVGetterFromLink(link= "https://docs.google.com/spreadsheets/d/1Pg6hx6uf_eBjoMy4olOVbUU13Z1XkeOAV0VYoxJnpL4/export?format=csv")
public class BankPersonTest {


    @Column(head = "A28")
    private String id;
    @Column(head = "B28")
    private String name;

    @Column(head = "C28")
    private long income;

    @Column(head = "D28")
    private long costs;

    @Column(head = "E28")
    private long remainder;

    @Override
    public String toString() {
        return "BankPersonTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", income=" + income +
                ", costs=" + costs +
                ", remainder=" + remainder +
                '}';
    }


    private static Table<BankPersonTest> csvObject;
    public static BankPersonTest[] values() {
        loadCSVObject();

        return csvObject.getObjects().toArray(new BankPersonTest[]{});
    }
    public static BankPersonTest valueOf(String name){
        loadCSVObject();

        for (BankPersonTest object : csvObject.getObjects()) {
            if(object.id.equals(name))
                return object;
        }
        throw new IllegalArgumentException();
    }

    private static void loadCSVObject() {
        if (csvObject == null)
            csvObject = CSVParser.getInstance().getCSVObject(BankPersonTest.class);
    }


}
