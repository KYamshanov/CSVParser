package ru.undframe;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CSVParserTest {

    @Test
    public void mainTest() {
        Parser parser = CSVParser.getInstance();
        parser.launch("ru.undframe");
        CSVObject<TestTable> csvObject = parser.getCSVObject(TestTable.class);
        assertEquals(csvObject.getObjects().get(0), new TestTable("a0", "b0", 1));
    }

}