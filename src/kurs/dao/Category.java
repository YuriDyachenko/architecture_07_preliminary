package kurs.dao;

import java.util.Objects;

public class Category extends Data {
    private final int id;
    private final String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(String s) {
        int id = 0;
        String name = null;

        String[] arr = s.split("\t");
        if (arr.length != 0) {
            id = Integer.parseInt(arr[0]);
            if (arr.length > 1) {
                name = arr[1];
            }
        }

        this.id = id;
        this.name = (name == null) ? "" : name;
    }

    @Override
    public String getString() {
        return id +
                "\t" +
                name +
                "\n";
    }

    @Override
    public boolean isWrong() {
        return (id == 0) || (name == null);
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Категория {" + id + ", '" + name + "'}";
    }
}
