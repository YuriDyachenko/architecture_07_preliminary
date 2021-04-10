package kurs.view;

import kurs.controller.Controller;
import java.util.Scanner;

public class Browser {
    private final Controller controller;
    private final StringBuilder cmdBuilder;
    private final StringBuilder inputBuilder;
    private boolean startOut;
    private boolean startIn;
    private boolean startCmd;

    public Browser(Controller controller) {
        this.controller = controller;
        cmdBuilder = new StringBuilder();
        inputBuilder = new StringBuilder();
    }

    private void clearAll() {
        cmdBuilder.setLength(0);
        inputBuilder.setLength(0);
        startOut = false;
        startIn = false;
        startCmd = false;
    }

    private void outHead() {
        System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("HROM:), ver 0.1");
    }

    private boolean checkStartOut(String s) {
        if (!s.equals(Controller.PREF_OUT)) return false;
        return startOut = true;
    }

    private boolean checkStartIn(String s) {
        if (!s.equals(Controller.PREF_IN)) return false;
        return startIn = true;
    }

    private boolean checkStartCmd(String s) {
        if (!s.equals(Controller.PREF_CMD)) return false;
        return startCmd = true;
    }

    private boolean checkExit(String s) {
        if (!s.equals(Controller.CMD_EXIT)) return false;
        System.out.println("\nПока...");
        return true;
    }

    private void buildCmd(String s) {
        cmdBuilder.append(s);
        if (inputBuilder.length() != 0) {
            cmdBuilder.append("\n").
                    append(inputBuilder.toString());
        }
    }

    private boolean hasStartIn(String s) {
        if (!startIn) return false;
        Scanner scanner = new Scanner(System.in);
        System.out.print(s);
        inputBuilder.append(scanner.nextLine());
        return true;
    }

    public void execute() {
        while (true) {
            outHead();
            String response = controller.perform(cmdBuilder.toString());
            clearAll();
            String[] respArr = response.split("\n");
            for (String s: respArr) {
                if (checkStartOut(s)) continue;
                if (checkStartIn(s)) continue;
                if (checkStartCmd(s)) continue;
                if (startCmd) {
                    if (checkExit(s)) return;
                    buildCmd(s);
                    break;
                }
                if (hasStartIn(s)) continue;
                if (startOut) System.out.println(s);
            }
        }
    }
}
