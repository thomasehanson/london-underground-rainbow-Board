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
    String id;
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
        this.updateStatus();
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

    public String getStatus() {
        return this.status;
    }

    public void updateStatus(){
        try {
            // Will use the TfL API to get the current status of the line

            Constants constants = new Constants(); // Instantiate class that holds API Keys

            // URL for the request that will return the disruptions for the given line id
            //                              API URL                  Line ID        Disruptions             API Keys
            URL url = new URL("https://api.tfl.gov.uk/Line/"+ (String)id +"/Status?app_key="+ constants.getAppKey());

            // HTTP(S) connection settings
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection(); // Open connection with API
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode(); //HTTP code

            if (responseCode == HttpURLConnection.HTTP_OK){ // Hence request was successful

                // Copy contents of request to string "response"
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // If no disruptions, set status as "Good Service"
                String strResponse = response.toString();
                if (!strResponse.equals("[]"))
                {
                    // Convert response into json
                    JSONParser jsonParser = new JSONParser();
                    JSONArray disruptionArray = (JSONArray) jsonParser.parse(strResponse); // Gets whole array
                    JSONObject disruptionObject = (JSONObject) disruptionArray.get(0); // Gets first Object in []s
                    String lineStatusesStr = (disruptionObject.get("lineStatuses")).toString();// Gets line statuses JSON
                    JSONArray lineStatusesArray = (JSONArray) jsonParser.parse(lineStatusesStr); // Parses lineStatus back to JSON
                    JSONObject lineStatuses = (JSONObject) lineStatusesArray.get(0); // Gets first Object in []
                    this.status = (lineStatuses.get("statusSeverityDescription")).toString();// Gets status in string form


                }

            }
        } catch (Exception e){
            System.err.println("Error: " +e);
            e.printStackTrace(System.err);
        }
    }
}

