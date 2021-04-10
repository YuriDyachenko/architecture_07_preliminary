package kurs;

import kurs.controller.Controller;
import kurs.view.Browser;

public class Main {

    public static void main(String[] args) {

        new Browser(new Controller()).execute();

    }
}
