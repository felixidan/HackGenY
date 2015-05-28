package com.felixidan.example.networkcalldemo;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.CharBuffer;

/**
 * Created by idan on 29/05/2015.
 */
public class NetworkHelper {

    public Double getTemp() {
        String url = "http://api.openweathermap.org/data/2.1/weather/city/293397?type=json&units=metric";
        try {
            String jsonString = downloadUrl(url);
            WeatherResponse response = parseResponse(jsonString);
            double temp = response.main.temp;
            return new Double(temp);
        } catch (IOException e) {
            return null;
        }
    }

    private WeatherResponse parseResponse(String jsonString) {
        Gson gson = new Gson();
        final WeatherResponse response = gson.fromJson(jsonString, WeatherResponse.class);
        return response;
    }

    // Given a URL, establishes an HttpUrlConnection and retrieves
// the web page content as a InputStream, which it returns as
// a string.
    private String downloadUrl(String address) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(address);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = readIt(is);
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    // Reads an InputStream and converts it to a String.
    public String readIt(InputStream stream ) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }

        return total.toString();
    }
}
