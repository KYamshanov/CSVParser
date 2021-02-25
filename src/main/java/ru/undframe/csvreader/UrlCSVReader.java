package ru.undframe.csvreader;

import ru.undframe.CSVTable;
import ru.undframe.CSVReader;
import ru.undframe.DataReader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@CSVReader(supportClasses = String.class)
public class UrlCSVReader implements DataReader<String> {
    @Override
    public CSVTable read(String string) throws IOException {
        System.out.println("Download " + string);

        URL url = new URL(string);

        Scanner s = new Scanner(url.openStream());
        List<String> lines = new ArrayList<>();
        while (s.hasNext())
            lines.add(s.nextLine());

        System.out.println("CSV downloaded");

        s.close();

        return new CSVTable(lines, ',');
    }
}
