package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class GUI {

    public static void main(String[] args){

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

        for (String mode : modes) {
            Mode modeInst = new Mode(mode);
            modeInst.updateStatus();
            Hashtable<String, String> lineStats = modeInst.getStatuses();

            for (Map.Entry entry : lineStats.entrySet()) {
                lineNum++;
                JLabel name = new JLabel();
                String id = entry.getKey().toString();
                name.setText(constants.getLineName(id));
                name.setFont(johnston);
                name.setOpaque(true);
                name.setBackground(constants.getLineColour(id));
                name.setForeground(constants.getTextColour(id));
                name.setHorizontalTextPosition(JLabel.CENTER);
                name.setHorizontalAlignment(JLabel.CENTER);

                JLabel dis = new JLabel();
                dis.setText(entry.getValue().toString());
                dis.setFont(johnston);
                dis.setHorizontalTextPosition(JLabel.CENTER);
                dis.setHorizontalAlignment(JLabel.CENTER);

                lineLabArray.add(name);
                disLabArray.add(dis);
                myFrame.add(name);
                myFrame.add(dis);
            }

            myFrame.setLayout(new GridLayout(lineNum, 2));
        }
        myFrame.setVisible(true);

    }
}
