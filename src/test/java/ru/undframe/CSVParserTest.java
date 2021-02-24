package ru.undframe;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CSVParserTest {

    @Test
    public void mainTest() {
        Parser parser = CSVParser.getInstance();
        try {
            parser.launch("ru.undframe");
        } catch (IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
        Table<TestTable> csvObject = parser.getCSVObject(TestTable.class);
        assertEquals(csvObject.getObjects().get(0), new TestTable("a0", "b0", 1,new MultiplayObject("vasya",500),"A",90,new Matrix("heath","age",90,15),56));
        assertNotEquals(csvObject.getObjects().get(0), new TestTable("a0youlox", "b0", 1,new MultiplayObject("vasya",100),"A",90,new Matrix("heath","age",90,15),56));
        assertEquals(csvObject.getObjects().get(1), new TestTable("a1", "b1", -1,new MultiplayObject("igron",1500),"B",95,new Matrix("heath","age",90,15),56));
        assertEquals(csvObject.getObjects().size(), 21,95);
    }


    @Test
    public void testFileTable(){
        Parser parser = CSVParser.getInstance();
        try {
            parser.launch("ru.undframe");
        } catch (IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }
        Table<TestTable3> csvObject = parser.getCSVObject(TestTable3.class);

        System.out.println(csvObject.getData());


        for (TestTable3 object : csvObject.getObjects()) {



            System.out.println(object.getName()+" "+object.getRemainder());

        }



    }

    @Test
    public void testCustomEnum(){

        Parser parser = CSVParser.getInstance();
        try {
            parser.launch("ru.undframe");
        } catch (IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }

        testSpeed("Custom",() -> {
            for (BankPersonTest value : BankPersonTest.values()) {
                System.out.println(value);
            }
        });
        testSpeed("Default",() -> {
            for (BanPersonEnum value : BanPersonEnum.values()) {
                System.out.println(value);
            }
        });

    }


    public static void testSpeed(String mrk,Runnable runnable){
        long start = System.nanoTime();
        runnable.run();
        long stop = System.nanoTime();

        System.out.println("["+mrk+ "] ACTION execute with "+ (stop-start));

    }

}