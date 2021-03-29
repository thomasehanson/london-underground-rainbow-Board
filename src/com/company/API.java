package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

// API class contains methods used to pull data from Tfl API
public class API {
    Constants constants = new Constants();
    String appKey = constants.getAppKey();

    // Gets the line status of for given line ids
    public String lineStatusForGivenLine(String id) {
        String status = new String();
        Hashtable<String, String> statuses = new Hashtable<String, String>();
            try {

                // URL for the request that will return the disruptions for the given line id
                //                              API URL                    Line ID        Disruptions      API Key
                URL url = new URL("https://api.tfl.gov.uk/Line/" + (String) id + "/Status?app_key=" + appKey);

                // HTTP(S) connection settings
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection(); // Open connection with API
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode(); //HTTP code

                if (responseCode == HttpURLConnection.HTTP_OK) { // Hence request was successful

                    // Copy contents of request to string "response"
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // If no disruptions, set status as "Good Service"
                    String strResponse = response.toString();
                    if (!strResponse.equals("[]")) {
                        // Convert response into json
                        JSONParser jsonParser = new JSONParser();
                        JSONArray disruptionArray = (JSONArray) jsonParser.parse(strResponse); // Gets whole array
                        JSONObject disruptionObject = (JSONObject) disruptionArray.get(0); // Gets first Object in []s
                        String lineStatusesStr = (disruptionObject.get("lineStatuses")).toString();// Gets line statuses JSON
                        JSONArray lineStatusesArray = (JSONArray) jsonParser.parse(lineStatusesStr); // Parses lineStatus back to JSON
                        JSONObject lineStatuses = (JSONObject) lineStatusesArray.get(0); // Gets first Object in []
                        status = (lineStatuses.get("statusSeverityDescription")).toString();// Gets status in string form

                    }

                }
            } catch (Exception e) {
                status = e.toString();
            }
        return status;
    }

    // Gets the line status of for given line ids
    public Hashtable<String, String> lineStatusForGivenLines(String[] ids) {
        String status = new String();
        Hashtable<String, String> statuses = new Hashtable<String, String>();
        for (String id : ids) {
            try {

                // URL for the request that will return the disruptions for the given line id
                //                              API URL                    Line ID        Disruptions      API Key
                URL url = new URL("https://api.tfl.gov.uk/Line/" + (String) id + "/Status?app_key=" + appKey);

                // HTTP(S) connection settings
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection(); // Open connection with API
                con.setRequestMethod("GET");
                int responseCode = con.getResponseCode(); //HTTP code

                if (responseCode == HttpURLConnection.HTTP_OK) { // Hence request was successful

                    // Copy contents of request to string "response"
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    // If no disruptions, set status as "Good Service"
                    String strResponse = response.toString();
                    if (!strResponse.equals("[]")) {
                        // Convert response into json
                        JSONParser jsonParser = new JSONParser();
                        JSONArray disruptionArray = (JSONArray) jsonParser.parse(strResponse); // Gets whole array
                        JSONObject disruptionObject = (JSONObject) disruptionArray.get(0); // Gets first Object in []s
                        String lineStatusesStr = (disruptionObject.get("lineStatuses")).toString();// Gets line statuses JSON
                        JSONArray lineStatusesArray = (JSONArray) jsonParser.parse(lineStatusesStr); // Parses lineStatus back to JSON
                        JSONObject lineStatuses = (JSONObject) lineStatusesArray.get(0); // Gets first Object in []
                        status = (lineStatuses.get("statusSeverityDescription")).toString();// Gets status in string form

                        statuses.put(id, status);


                    }

                }
            } catch (Exception e) {
                status = e.toString();
            }
        }
        return statuses;
    }

    public Hashtable<String, String> lineStatusForGivenMode(String modeId) {
        Hashtable<String, String> statuses = new Hashtable<String, String>();
        try {
            // Will use the TfL API to get the current status of the line

            Constants constants = new Constants(); // Instantiate class that holds API Keys

            // URL for the request that will return the disruptions for the given line id
            //                                  API URL                     Line ID           Status       API Keys
            URL url = new URL("https://api.tfl.gov.uk/Line/Mode/"+ (String)modeId +"/Status?app_key="+ appKey);

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
                            statuses.put(lineId, status);
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
        return statuses;
    }
}
