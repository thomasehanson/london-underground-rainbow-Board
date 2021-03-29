package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class LineManager {
    // Creates lines from the constant class of line ids, returning a dictionary of line objects with their line id as the key

    API api = new API();
    Constants constants = new Constants();
    String[] lineIds = constants.getLineIds();
    Hashtable<String, Line> lineDict = new Hashtable<String, Line>();
    Hashtable<String, Mode> modeDict = new Hashtable<String, Mode>();
    Hashtable<String, JLabel> nameLabelDict = new Hashtable<String, JLabel>();
    Hashtable<String, JLabel> statusLabelDict = new Hashtable<String, JLabel>();

    public void createLines(){
        for(String line : constants.getLineIds()){
            // Create Line object
            Line lineObject = new Line(line);
            this.lineDict.put(line, lineObject);

            // Font gubbins
            float fontsize = 22f;
            // Custom Johnston Font
            Font johnston = Font.getFont("Ariel");
            try {
                johnston = Font.createFont(Font.TRUETYPE_FONT, new File("johnston.ttf")).deriveFont(fontsize);
            } catch (IOException |FontFormatException e) {
            }
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Create Line labels
            JLabel name = new JLabel();
            name.setText(lineObject.getLineName());
            name.setFont(johnston);
            name.setOpaque(true);
            name.setBackground(lineObject.getLineColour());
            name.setForeground(lineObject.getTextColour());
            name.setHorizontalTextPosition(JLabel.CENTER);
            name.setHorizontalAlignment(JLabel.CENTER);
            nameLabelDict.put(line, name);

            JLabel status = new JLabel();
            status.setText(lineObject.getStatus());
            status.setFont(johnston);
            status.setHorizontalTextPosition(JLabel.CENTER);
            status.setHorizontalAlignment(JLabel.CENTER);
            statusLabelDict.put(line, status);
        }
    }

    public Line getLine(String id){
        return this.lineDict.get(id);
    }

    public void createModes(){
        for(String mode : constants.getModes()){
            Mode modeObject = new Mode(mode);
            this.modeDict.put(mode, modeObject);
        }
    }

    public Mode getMode(String id){
        return this.modeDict.get(id);
    }

    public void updateAllStatuses(){
        Hashtable<String, String> statuses = new Hashtable<String, String>(); // Create new Hashtable to store line status with its id as the key
        statuses = api.lineStatusForGivenLines(this.lineIds); // Use api to update status of all lines

        // Update status attribute for all lines
        for(String lineId : this.lineIds){
            Line line = this.lineDict.get(lineId);
            String status = statuses.get(lineId);
            line.setStatus(status);
            statusLabelDict.get(lineId).setText(status);
        }
    }

    public void addLabel(String type, String id, JLabel label){
        switch (type){
            case "name":
                nameLabelDict.put(id, label);
                break;
            case "status":
                statusLabelDict.put(id, label);
        }
    }

    public JLabel getLabelById(String type, String id){
        switch (type){
            case "name":
                return nameLabelDict.get(id);
            case "status":
                return statusLabelDict.get(id);
        }
        return new JLabel();
    }
}
