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

    public Mode(String mode) {
        // Add try...
        this.modeId = mode; // Id of mode for TfL API
        // ...catch sets default values
    }

    public Hashtable<String, String> getStatuses(){
        return this.statuses;
    }

    public void updateStatus(){
        try {
            // Will use the TfL API to get the current status of the line

            Constants constants = new Constants(); // Instantiate class that holds API Keys

            // URL for the request that will return the disruptions for the given line id
            //                              API URL                  Line ID        Disruptions             API Keys
            URL url = new URL("https://api.tfl.gov.uk/Line/Mode/"+ (String)modeId +"/Status?app_key="+ constants.getAppKey());

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
                    JSONArray modeStatusArray = (JSONArray) jsonParser.parse(strResponse); // Gets whole array
                    int i = 0;
                    // Get line statuses until theres an error (aka there are no more lines on that mode)

                    while(!strResponse.equals("[]")){
                        try {
                            JSONObject disruptionObject = (JSONObject) modeStatusArray.get(i); // Gets first Object in []s
                            String lineStatusesStr = (disruptionObject.get("lineStatuses")).toString();// Gets line statuses JSON
                            String lineId = (disruptionObject.get("id")).toString(); // Gets Line id
                            JSONArray lineStatusesArray = (JSONArray) jsonParser.parse(lineStatusesStr); // Parses lineStatus back to JSON
                            JSONObject lineStatuses = (JSONObject) lineStatusesArray.get(0); // Gets first Object in []
                            String status = (lineStatuses.get("statusSeverityDescription")).toString();// Gets status in string form
                            this.statuses.put(lineId, status);
                            i++;
                        }catch (Exception e){
                            break;
                        }
                    }

                }

            }
        } catch (Exception e){
            System.err.println("Error: " +e);
            e.printStackTrace(System.err);
        }
    }
}
