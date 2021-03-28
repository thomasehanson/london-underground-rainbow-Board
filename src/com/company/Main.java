package com.company;
import java.util.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Constants constants = new Constants();
        String[] modes = constants.getModes();

        for (String mode : modes){
            Mode modeInst = new Mode(mode);
            modeInst.updateStatus();
            Hashtable<String, String> lineStats = modeInst.getStatuses();

            for(Map.Entry entry : lineStats.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
}