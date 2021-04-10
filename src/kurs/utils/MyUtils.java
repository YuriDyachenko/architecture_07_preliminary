package kurs.utils;

public class MyUtils {

    public boolean oneOf(String part, String many) {
        String[] arr = many.split("\\|");
        for (String s: arr)
            if (s.equals(part)) return true;
        return false;
    }
}
