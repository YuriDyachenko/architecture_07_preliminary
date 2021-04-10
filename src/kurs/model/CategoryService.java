package kurs.model;

import kurs.dao.Category;
import kurs.dao.DataType;
import kurs.dao.FileDataTable;
import kurs.dao.Writer;

public class CategoryService {
    private final FileDataTable table;

    public CategoryService() {
        table = new FileDataTable("catogories.dat", DataType.CATEGORY);
    }

    public boolean insert(Category category) {
        Category tempCategory = new Category(table.getNextId(), category.getName());
        return table.insert(tempCategory);
    }

    public boolean update(Category category) {
        return table.update(category);
    }

    public boolean delete(Category category) {
        return table.delete(category);
    }

    public FileDataTable getTable() {
        return table;
    }

    public Category findById(int id) {
        return (Category) table.findById(id);
    }
}
