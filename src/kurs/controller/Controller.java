package kurs.controller;

import kurs.dao.Book;
import kurs.dao.Category;
import kurs.dao.Data;
import kurs.dao.Writer;
import kurs.model.BookService;
import kurs.model.CategoryService;
import kurs.model.WriterService;
import kurs.utils.MyUtils;

import java.util.ArrayList;

public class Controller {
    public static String PREF_IN = "#$in$#";
    public static String PREF_OUT = "#$out$#";
    public static String PREF_CMD = "#$cmd$#";
    public static String PREF_IN_LF = "#$in$#\n";
    public static String PREF_OUT_LF = "#$out$#\n";
    public static String PREF_CMD_LF = "#$cmd$#\n";
    public static String CMD_GET = "get";
    public static String CMD_EXIT = "exit";
    public static String CMD_EXIT_E = CMD_EXIT + "|e";
    public static String CMD_BACK = "return";
    public static String CMD_BACK_E = CMD_BACK + "|r";
    public static String WRITER = "writer";
    public static String WRITER_E = WRITER + "|w";
    public static String CATEGORY = "category";
    public static String CATEGORY_E = CATEGORY + "|c";
    public static String BOOK = "book";
    public static String BOOK_E = BOOK + "|b";

    private final WriterService ws;
    private final CategoryService cs;
    private final BookService bs;
    private final MyUtils mu;

    public Controller() {
        ws = new WriterService();
        cs = new CategoryService();
        bs = new BookService();
        mu = new MyUtils();
    }

    private String startPage() {
        return buildMenu(PREF_IN_LF + "enter command ",
                ": \n" + PREF_CMD_LF + CMD_GET,
                WRITER, CATEGORY, BOOK, CMD_EXIT);
    }

    private String exitPage() {
        return PREF_CMD_LF + CMD_EXIT;
    }

    private String errMsg(String msg) {
        return PREF_OUT_LF + "ERROR: " + msg + "\n";
    }

    private String buildMenu(String pref, String suff, String... s) {
        StringBuilder sb = new StringBuilder(pref + "(");
        for (int i = 0; i < s.length; i++) {
            sb.append(s[i]);
            if (i < s.length - 1) sb.append("|");
        }
        return sb.append(")").append(suff).toString();
    }

    private String backCmd() {
        return buildMenu(PREF_IN_LF + "enter command ",
                ": \n" + PREF_CMD_LF + CMD_GET,
                WRITER, CATEGORY, BOOK, CMD_BACK, CMD_EXIT);
    }

    public String perform(String command) {
        if (command.isEmpty()) return startPage();

        String[] commArr = command.split("\n");
        if (commArr.length != 0) {

            String localCommand = commArr[0];
            String localData = (commArr.length > 1) ? commArr[1] : "";

            if (localCommand.equals(CMD_GET)) return get(localData);
        }

        return errMsg("wrong command") + startPage();
    }

    public String post(String command, String data) {
        StringBuilder sb = new StringBuilder();
        if (command.equalsIgnoreCase("writer")) {
            ws.insert(new Writer(0, data));
            sb.append("OK\n");
        }
        return sb.toString();
    }

    private String get(String command) {
        if (mu.oneOf(command, CMD_EXIT_E)) return exitPage();
        if (mu.oneOf(command, CMD_BACK_E)) return startPage();

        StringBuilder sb = new StringBuilder();

        if (mu.oneOf(command, WRITER_E)) sb.append(getWriters());
        if (mu.oneOf(command, CATEGORY_E)) sb.append(getCategories());
        if (mu.oneOf(command, BOOK_E)) sb.append(getBooks());

        if (sb.length() == 0)
            return errMsg("perform - wrong command") + startPage();

        sb.append(backCmd());
        return PREF_OUT_LF + sb.toString();
    }

    private String getBooks() {
        StringBuilder sb = new StringBuilder();

        sb.append("КНИГИ:").append("\n");
        ArrayList<Data> list = bs.getTable().getAll();
        for (Data data: list) {
            Book book = (Book) data;
            Writer writer = ws.findById(book.getWriterId());
            Category category = cs.findById(book.getCategoryId());
            sb.append("  ")
                    .append(book.getId())
                    .append(", '")
                    .append(book.getName())
                    .append("', '")
                    .append(writer.getName())
                    .append("', '")
                    .append(category.getName())
                    .append("'\n");
        }

        return sb.toString();
    }

    private String getWriters() {
        StringBuilder sb = new StringBuilder();

        sb.append("ПИСАТЕЛИ:").append("\n");
        ArrayList<Data> list = ws.getTable().getAll();
        for (Data data: list) {
            Writer writer = (Writer) data;
            sb.append("  ")
                    .append(writer.getId())
                    .append(", '")
                    .append(writer.getName())
                    .append("'\n");
        }

        return sb.toString();
    }

    private String getCategories() {
        StringBuilder sb = new StringBuilder();

        sb.append("КАТЕГОРИИ:").append("\n");
        ArrayList<Data> list = cs.getTable().getAll();
        for (Data data: list) {
            Category category = (Category) data;
            sb.append("  ")
                    .append(category.getId())
                    .append(", '")
                    .append(category.getName())
                    .append("'\n");
        }

        return sb.toString();
    }
}
