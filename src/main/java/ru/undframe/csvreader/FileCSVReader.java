package ru.undframe.csvreader;

import ru.undframe.CSVTable;
import ru.undframe.DataReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileCSVReader implements DataReader<String> {
    @Override
    public CSVTable read(String string) throws IOException {
        System.out.println("Load file " + string);

        File file= new File(string);

        Scanner s = new Scanner(new FileReader(file));
        List<String> lines = new ArrayList<>();
        while (s.hasNext())
            lines.add(s.nextLine());

        System.out.println("CSV loaded");

        s.close();

        return new CSVTable(lines, ",");
    }
}
