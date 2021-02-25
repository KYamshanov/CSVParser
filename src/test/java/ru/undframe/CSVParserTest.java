package ru.undframe;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        assertEquals(csvObject.getObjects().get(0), new TestTable("a0", "b0", 1, new MultiplayObject("vasya", 500), "A", 90, new Matrix("heath", "age", 90, 15), 56));
        assertNotEquals(csvObject.getObjects().get(0), new TestTable("a0youlox", "b0", 1, new MultiplayObject("vasya", 100), "A", 90, new Matrix("heath", "age", 90, 15), 56));
        assertEquals(csvObject.getObjects().get(1), new TestTable("a1", "b1", -1, new MultiplayObject("igron", 1500), "B", 95, new Matrix("heath", "age", 90, 15), 56));
        assertEquals(csvObject.getObjects().size(), 21, 95);
    }


    @Test
    public void testCustomEnum() {

        Parser parser = CSVParser.getInstance();
        try {
            parser.launch("ru.undframe");
        } catch (IllegalAccessException | InstantiationException | IOException e) {
            e.printStackTrace();
        }

        testSpeed("Custom", () -> {
            for (BankPersonTest value : BankPersonTest.values()) {
                System.out.println(value);
            }
        });
        testSpeed("Default", () -> {
            for (BanPersonEnum value : BanPersonEnum.values()) {
                System.out.println(value);
            }
        });

    }


    public static void testSpeed(String mrk, Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        long stop = System.nanoTime();

        System.out.println("[" + mrk + "] ACTION execute with " + (stop - start));

    }

    @Test
    public void testSeparate(){

        String s = "a,b,,,\",\"\"\"";


        for (String s1 : separateLine(s, ',')) {
            System.out.println("["+s1+"]");
        }

    }

    public String[] separateLine(String line, char separateChar) {

        List<String> words = new ArrayList<>();

        char[] charArray = line.toCharArray();

        int beginIndex = 0;

        boolean stringMode = false;

        for (int index = 0; index < charArray.length; index++) {

            char symbol = charArray[index];

            if(symbol=='\"'){
                if(stringMode){
                    if((index+1)<charArray.length &&  charArray[index+1]=='\"'){
                        index++;
                        continue;
                    }
                }
                stringMode = !stringMode;
            }


            if(!stringMode)
                if (symbol == separateChar) {

                    if (beginIndex == index) {
                        words.add("");
                    } else {
                        String substring = line.substring(beginIndex, index);
                        words.add(substring);
                    }
                    beginIndex = index + 1;
                }

        }

        String substring = line.substring(beginIndex, charArray.length);
        if(substring.startsWith("\""))
            substring = substring.substring(1, substring.length() - 1);

        substring = substring.replaceAll("\"\"", "\"");

        words.add(substring);

        return words.toArray(new String[]{});

    }


}