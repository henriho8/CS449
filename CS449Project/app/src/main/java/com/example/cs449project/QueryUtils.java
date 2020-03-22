package com.example.cs449project;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // Empty ArrayList so we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        StringBuilder stringBuilder = new StringBuilder();
        URLConnection urlConnection;
        BufferedReader bufferedReader;

        try {

            url = new URL(requestUrl);

            urlConnection = url.openConnection();

            // wrapping the urlconnection in a bufferedreader
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Appending the line to StringBuilder
                stringBuilder.append(line);
            }
            // Closing BufferedReader
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            // Converting the string builder to String and using it as a JSON response
            jsonResponse = stringBuilder.toString();



            // Parsing the JSON Response
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Fetching 'features' array
            JSONArray jsonArray = (JSONArray) jsonObject.get("features");

            // Iterating the features
            for (int i = 0; i < jsonArray.length(); i++) {
                // Getting object properties
                JSONObject json = jsonArray.getJSONObject(i).getJSONObject("properties");
                System.out.println(json);
                // Passing data to the constructor
                Earthquake earthquake = new Earthquake((double)json.get("mag"), (String) json.get("place"),
                        (long)json.get("time"), (String) json.get("url"));

                // Adding each object earthquake to the list
                earthquakes.add(earthquake);
            }


        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}
