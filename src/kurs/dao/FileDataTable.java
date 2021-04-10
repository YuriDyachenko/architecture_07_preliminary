package kurs.dao;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileDataTable extends ArrayListDataTable {
    private final String fileName;
    private final DataType type;

    public FileDataTable(String name, DataType type) {
        super(name);
        fileName = name;
        this.type = type;
        readFromFile();
    }

    @Override
    public boolean saveToStorage() {
        return saveToFile();
    }

    private void readFromFile() {
        DataFactory dataFactory = new DataFactory(type);

        deleteAll();
        clearLastError();
        try {
            FileReader fileReader = new FileReader(fileName);
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                insertInternal(dataFactory.createFromString(scanner.nextLine()));
            }
            fileReader.close();
        } catch (Exception e) {
            invokeError("read from file - " + e.getMessage());
        }

        itsOk();
    }

    private boolean saveToFile() {
        DataFactory dataFactory = new DataFactory(type);

        clearLastError();
        try {
            FileWriter fileWriter = new FileWriter(fileName, false);
            ArrayList<Data> list = getAll();
            for (Data o: list) {
                fileWriter.write(dataFactory.dataToString(o));
            }
            fileWriter.flush();
        } catch (IOException e) {
            invokeError("save to file - " + e.getMessage());
        }

        return itsOk();
    }
}
