package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

public class Mode {
    String modeId;
    Hashtable<String, String> statuses = new Hashtable<String, String>();

    // Custom constructor
    public Mode(String mode) {
        // Add try...
        this.modeId = mode; // Id of mode for TfL API
        // ...catch sets default values
    }

    public Hashtable<String, String> getStatuses(){
        return this.statuses;
    }

    public void updateStatus(){
        API api = new API();
        this.statuses = api.lineStatusForGivenMode(modeId);
    }
}
