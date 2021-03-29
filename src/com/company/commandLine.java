package com.company;
import java.util.*;
import java.awt.*;

// Running CommandLine will simply output the current status of all tube lines, right in the command line
public class commandLine {

    public static void main(String[] args) {
        Constants constants = new Constants();
        LineManager lineManager = new LineManager();

        lineManager.createLines();
        lineManager.updateAllStatuses();


        for(String line : constants.getLineIds()) {
            System.out.println(lineManager.getLine(line).getLineName()+ ": " + lineManager.getLine(line).getStatus());
        }
    }
}