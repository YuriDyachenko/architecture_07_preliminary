package kurs.dao;

import java.util.Objects;

public class Book extends Data {
    private final int id;
    private final String name;
    private final int writerId;
    private final int categoryId;

    public Book(int id, String name, int writerId, int categoryId) {
        this.id = id;
        this.name = name;
        this.writerId = writerId;
        this.categoryId = categoryId;
    }

    public Book(String s) {
        int id = 0;
        String name = null;
        int writerId = 0;
        int categoryId = 0;

        String[] arr = s.split("\t");
        if (arr.length != 0) {
            id = Integer.parseInt(arr[0]);
            if (arr.length > 1) {
                name = arr[1];
                if (arr.length > 2) {
                    writerId = Integer.parseInt(arr[2]);
                    if (arr.length > 3) {
                        categoryId = Integer.parseInt(arr[3]);
                    }
                }
            }
        }

        this.id = id;
        this.name = name;
        this.writerId = writerId;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isWrong() {
        return (id == 0) || (name == null) || (writerId == 0) || (categoryId == 0);
    }

    public int getWriterId() {
        return writerId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    @Override
    public String getString() {
        return id +
                "\t" +
                name +
                "\t" +
                writerId +
                "\t" +
                categoryId +
                "\n";
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Книга {" + id + ", '" + name + "', " + writerId + ", " + categoryId + "}";
    }
}
