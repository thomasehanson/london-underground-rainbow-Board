package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class GUI {

    public static void main(String[] args) throws InterruptedException {

        LineManager lineManager = new LineManager();
        lineManager.createLines();
        lineManager.updateAllStatuses();

        float fontsize = 22f;
        // Custom Johnston Font
        Font johnston = Font.getFont("Ariel");
        try {
            johnston = Font.createFont(Font.TRUETYPE_FONT, new File("johnston.ttf")).deriveFont(fontsize);
        } catch (IOException |FontFormatException e) {
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

        MyFrame myFrame = new MyFrame();
        JLabel label = new JLabel();
        ArrayList<JLabel> lineLabArray = new ArrayList<JLabel>();
        ArrayList<JLabel> disLabArray = new ArrayList<JLabel>();
        Constants constants = new Constants();
        String[] modes = constants.getModes();
        int i = 0;
        int lineNum = 0;

        // Creates

        for (String line : constants.getLineIds()) {
            JLabel nameLabel = lineManager.getLabelById("name", line);
            JLabel statusLabel = lineManager.getLabelById("status", line);
            myFrame.add(nameLabel);
            myFrame.add(statusLabel);
        }

        myFrame.setLayout(new GridLayout(lineNum, 2));
        myFrame.setVisible(true);

        while(true){
            lineManager.updateAllStatuses();
            TimeUnit.MINUTES.sleep(5);
        }
    }


}
