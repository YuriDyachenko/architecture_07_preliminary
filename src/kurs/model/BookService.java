package kurs.model;

import kurs.dao.Book;
import kurs.dao.Category;
import kurs.dao.DataType;
import kurs.dao.FileDataTable;

public class BookService {
    private final FileDataTable table;

    public BookService() {
        table = new FileDataTable("books.dat", DataType.BOOK);
    }

    public boolean insert(Book book) {
        Book tempBook = new Book(table.getNextId(), book.getName(), book.getWriterId(), book.getCategoryId());
        return table.insert(tempBook);
    }

    public boolean update(Book book) {
        return table.update(book);
    }

    public boolean delete(Book book) {
        return table.delete(book);
    }

    public FileDataTable getTable() {
        return table;
    }

}
