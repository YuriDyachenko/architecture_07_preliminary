package kurs.dao;

import kurs.utils.Err;

import java.util.ArrayList;

public class ArrayListDataTable extends DataTable {
    private final String name;
    private final ArrayList<Data> list;
    private Err lastError;
    private int lastId;

    public ArrayListDataTable(String name) {
        this.name = name;
        list = new ArrayList<>();
        lastId = 0;
        clearLastError();
    }

    @Override
    public void invokeError(String msg) {
        lastError = new Err(this.getClass().getSimpleName() + " -> " + name + ": " + msg);
    }

    @Override
    public boolean saveToStorage() {
        return true;
    }

    @Override
    public void clearLastError() {
        lastError = null;
    }

    @Override
    public boolean itsOk() {
        return (lastError == null);
    }

    @Override
    public String getLastErrorMsg() {
        if (itsOk()) return "";
        return lastError.getMsg();
    }

    @Override
    public boolean deleteAll() {
        clearLastError();
        list.clear();
        return itsOk();
    }

    @Override
    public ArrayList<Data> getAll() {
        clearLastError();
        return (ArrayList<Data>) list.clone();
    }

    @Override
    public int getNextId() {
        return lastId + 1;
    }

    public boolean insertInternal(Data o) {
        clearLastError();
        if (o != null) {
            if (list.contains(o)) {
                invokeError("insert - already exists " + o);
            } else {
                list.add(o);
                int id = o.getId();
                if (id > lastId) {
                    lastId = id;
                }
            }
        }
        return itsOk();
    }

    @Override
    public boolean insert(Data o) {
        boolean result = insertInternal(o);
        if (result) {
            saveToStorage();
        }
        return result;
    }

    @Override
    public boolean update(Data o) {
        clearLastError();
        if (!list.contains(o)) {
            invokeError("update - does not exist " + o);
        } else {
            list.set(list.indexOf(o), o);
            saveToStorage();
        }
        return itsOk();
    }

    @Override
    public boolean delete(Data o) {
        clearLastError();
        if (!list.contains(o)) {
            invokeError("delete - does not exist " + o);
        } else {
            if (o.getId() == lastId) {
                lastId--;
            }
            list.remove(o);
            saveToStorage();
        }
        return itsOk();
    }

    @Override
    public Data findById(int id) {
        clearLastError();
        for (Data o: list) {
            if (o.getId() == id) {
                return o;
            }
        }
        invokeError("findById - does not exist by id " + id);
        return null;
    }
}
