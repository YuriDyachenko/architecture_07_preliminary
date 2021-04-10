package kurs.model;

import kurs.dao.DataType;
import kurs.dao.FileDataTable;
import kurs.dao.Writer;

public class WriterService {
    private final FileDataTable table;

    public WriterService() {
        table = new FileDataTable("writers.dat", DataType.WRITER);
    }

    public boolean insert(Writer writer) {
        Writer tempWriter = new Writer(table.getNextId(), writer.getName());
        return table.insert(tempWriter);
    }

    public boolean update(Writer writer) {
        return table.update(writer);
    }

    public boolean delete(Writer writer) {
        return table.delete(writer);
    }

    public FileDataTable getTable() {
        return table;
    }

    public Writer findById(int id) {
        return (Writer) table.findById(id);
    }
}
