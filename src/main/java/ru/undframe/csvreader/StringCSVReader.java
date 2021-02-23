package ru.undframe.csvreader;

import ru.undframe.ArrayTable;
import ru.undframe.CSVReader;
import ru.undframe.Reader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@CSVReader(supportClasses = String.class)
public class StringCSVReader implements Reader<String> {
    @Override
    public ArrayTable read(String csvUrl) throws IOException {
        System.out.println("Download " + csvUrl);
        URL url = new URL(csvUrl);
        Scanner s = new Scanner(url.openStream());
        List<String> lines = new ArrayList<>();
        while (s.hasNext())
            lines.add(s.nextLine());

        System.out.println("CSV downloaded");

        return new ArrayTable(lines, ",");
    }
}
