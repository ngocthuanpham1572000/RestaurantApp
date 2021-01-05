package com.example.parkingadmin;

import android.net.Uri;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUnit {

   // private  static final String BOOK_BASE_URL = "10.0.2";
//
//    private  static final String QUERY_PARAM = "q";
//
//
//    private static final String MAX_RESULTS = "maxResults";
//
//    private  static final String PRINT_TYPE = "printType";


    public static String getMoAn() {
        Uri builtURI = Uri.parse("http://10.0.2.2:8000/api/monan").buildUpon()

                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "GET");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String SetupBan(int id,int SoNguoi) {
//        JSONObject json =new JSONObject();
//        try {
//            json.put("id",id);
//            json.put("SoNguoi",SoNguoi);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String data=json.toString();
        Log.d("gan", String.valueOf(id));
        Uri builtURI = Uri.parse("http://10.0.2.2:8000/api/setupban").buildUpon()
                .appendQueryParameter("id", String.valueOf(id))
                .appendQueryParameter("SoNguoi", String.valueOf(SoNguoi))
                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "PUT");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String getDangNhap() {
        Uri builtURI = Uri.parse("http://10.0.2.2:8000/api/nhanvien").buildUpon().build();

        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "GET");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String getBan() {
        Uri builtURI = Uri.parse("http://10.0.2.2:8000/api/ban").buildUpon()
                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "GET");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String callAPI(URL requestURL, String method) {
        HttpURLConnection urlConnection = null;
        String result = "";
        try {

            urlConnection = (HttpURLConnection) requestURL.openConnection();

            urlConnection.setRequestMethod(method);


            urlConnection.connect();

            // Get the InputStream.
            InputStream inputStream = urlConnection.getInputStream();

            result = convertISToString(inputStream);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        Log.d("Log_js",result.toString());
        return result;
    }

    public static String convertISToString(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
                if (builder.length() == 0) {
                    return null;
                }
            }
        } catch (IOException e) {
            return null;
        }
        return builder.toString();
    }
}
