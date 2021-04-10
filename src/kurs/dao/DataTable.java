package kurs.dao;

import java.util.ArrayList;

public abstract class DataTable {
    public abstract ArrayList<Data> getAll();
    public abstract int getNextId();
    public abstract boolean insert(Data o);
    public abstract boolean update(Data o);
    public abstract boolean delete(Data o);
    public abstract Data findById(int id);
    public abstract boolean itsOk();
    public abstract boolean deleteAll();
    public abstract String getLastErrorMsg();
    public abstract void clearLastError();
    public abstract void invokeError(String msg);
    public abstract boolean saveToStorage();
}
