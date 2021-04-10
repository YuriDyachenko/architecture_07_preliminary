package kurs.dao;

public class DataFactory {
    private final DataType type;

    public DataFactory(DataType type) {
        this.type = type;
    }

    public String dataToString(Data o) {
        return o.getString();
    }

    public Data createFromString(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        switch (type) {
            case WRITER: {
                Writer o = new Writer(s);
                return o.isWrong() ? null : o;
            }
            case CATEGORY: {
                Category o = new Category(s);
                return o.isWrong() ? null : o;
            }
            case BOOK: {
                Book o = new Book(s);
                return o.isWrong() ? null : o;
            }
            default: return null;
        }
    }
}
