package kurs.dao;

import java.util.Objects;

public class Writer extends Data {
    private final int id;
    private final String name;

    public Writer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Writer(String s) {
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
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isWrong() {
        return (id == 0) || (name == null);
    }

    @Override
    public String getString() {
        return id +
                "\t" +
                name +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return id == writer.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Писатель {" + id + ", '" + name + "'}";
    }
}
