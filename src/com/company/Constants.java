package com.company;

import java.awt.*;
import java.util.Hashtable;

public class Constants {

    private String appKey = new String("736e31d89eb549b29ef9c85e514bcc35");
    private String secondaryAppKey = new String("3a877150da3642129130d60f82242bb1");

    private String[] lineIds = {"bakerloo", "central", "circle", "district", "hammersmith-city", "jubilee", "metropolitan",
            "northern", "piccadilly", "victoria", "waterloo-city", "dlr", "tram", "london-overground"};

    private String[] modes = {"tube", "dlr", "tram", "overground"};

    public String[] getLineIds() {
        return this.lineIds;
    }

    public String[] getModes() {
        return this.modes;
    }

    public String getlineId(int index){
        return this.lineIds[index];
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getSecondaryAppKey() {
        return this.secondaryAppKey;
    }

    public Color getLineColour(String colourCode) {
        Hashtable<String, Color> colours = new Hashtable<String, Color>();
        // Line/mode colours
        colours.put("bakerloo", new Color(178, 99, 0));
        colours.put("central", new Color(220, 36, 31));
        colours.put("circle", new Color(255, 211, 41));
        colours.put("district", new Color(0, 125, 50));
        colours.put("hammersmith-city", new Color(244, 169, 190));
        colours.put("jubilee", new Color(161, 165, 167));
        colours.put("metropolitan", new Color(155, 0, 88));
        colours.put("northern", new Color(0, 0, 0));
        colours.put("piccadilly", new Color(0, 25, 168));
        colours.put("victoria", new Color(0, 152, 216));
        colours.put("waterloo-city", new Color(147, 206, 186));
        colours.put("london-overground", new Color(239, 123, 16));
        colours.put("elizabeth", new Color(147, 100, 204));
        colours.put("dlr", new Color(0, 175, 173));
        colours.put("tram", new Color(0, 189, 25));

        // Misc
        colours.put("corpBlue", new Color(0, 25, 168));

        return colours.get(colourCode);
    }

    public Color getTextColour(String colourCode) {
        Color corpBlue = getLineColour("corpBlue");
        Hashtable<String, Color> colours = new Hashtable<String, Color>();
        colours.put("bakerloo", new Color(255,255,255));
        colours.put("central", new Color(255,255,255));
        colours.put("circle", corpBlue);
        colours.put("district", new Color(255,255,255));
        colours.put("hammersmith-city", corpBlue);
        colours.put("jubilee", new Color(255,255,255));
        colours.put("metropolitan", new Color(255,255,255));
        colours.put("northern", new Color(255,255,255));
        colours.put("piccadilly", new Color(255,255,255));
        colours.put("waterloo-city", corpBlue);
        colours.put("victoria", new Color(255,255,255));
        colours.put("london-overground", new Color(255,255,255));
        // Elizabeth line will go here
        colours.put("dlr", Color.white);
        colours.put("tram", Color.white);

        return colours.get(colourCode);
    }

    public String getLineName(String id) {
        Hashtable<String, String> names = new Hashtable<String, String>();
        names.put("bakerloo", "Bakerloo");
        names.put("central", "Central");
        names.put("circle", "Circle");
        names.put("district", "District");
        names.put("hammersmith-city", "H'smith & City");
        names.put("jubilee", "Jubilee");
        names.put("metropolitan", "Metropolitan");
        names.put("northern", "Northern");
        names.put("piccadilly", "Piccadilly");
        names.put("waterloo-city", "Waterloo & City");
        names.put("victoria", "Victoria");
        names.put("london-overground", "Overground");
        // Elizabeth line will go here
        names.put("dlr", "DLR");
        names.put("tram", "Tram");
        return names.get(id);
    }
}
