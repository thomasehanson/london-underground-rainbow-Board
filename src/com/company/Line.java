package com.company;
import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jdk.jshell.execution.Util;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

public class Line {
    String id = new String();
    Color lineColour;
    Color textColour;
    String lineName;
    String status;

    // Custom constructor
    public Line(String lineId) {
        // Add try...
        Constants constants = new Constants(); // Instantiate class containing all the constants
        this.id = lineId; // Id of line for TfL API
        this.lineName = constants.getLineName(id);
        this.lineColour = constants.getLineColour(id);
        this.textColour = constants.getTextColour(id);
        // ...catch sets default values
    }

    public String getId() {
        return this.id;
    }

    public String getLineName() {
        return this.lineName;
    }

    public Color getLineColour() {
        return this.lineColour;
    }

    public Color getTextColour() {
        return this.textColour;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getStatus() {
        return this.status;
    }

    public void updateStatus(){
        API api = new API();
        this.status = api.lineStatusForGivenLine(this.id);
    }
}

