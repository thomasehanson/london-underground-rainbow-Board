package com.company;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    MyFrame(){
        Constants constants = new Constants();
        this.setTitle("Rainbow Board");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,480);
        this.setResizable(false);
        ImageIcon TfLFav = new ImageIcon("tflfav.jpg"); // Creates ImageIcon of TfL logo
        this.setIconImage(TfLFav.getImage());
        Color bgColour = Color.white;
        this.getContentPane().setBackground(bgColour);

        // this.setExtendedState(Frame.MAXIMIZED_BOTH);
        // this.setUndecorated(true);
    }
}
