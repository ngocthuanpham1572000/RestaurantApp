package com.example.parkingadmin.function;

import android.net.Uri;
import android.util.Log;

import com.example.parkingadmin.model.HoaDon;
import com.example.parkingadmin.model.ThucDon;

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
    private  static final  String chuoi="http://10.0.2.2:8000/api/";

    public static String getMoAn() {
        Uri builtURI = Uri.parse(chuoi+"monan").buildUpon()

                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "GET");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String SetupBan(int id,int SoNguoi) {


        Uri builtURI = Uri.parse(chuoi+"setupban").buildUpon()
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
    public static String DoiMK(int id, String mk){
        Uri builtURI = Uri.parse(chuoi+"nhanvien").buildUpon()
                .appendQueryParameter("id", String.valueOf(id))
                .appendQueryParameter("MatKhau",mk)
                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "PUT");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String CloseBan(int id){
        Uri builtURI = Uri.parse(chuoi+"dongban").buildUpon()
                .appendQueryParameter("id",String.valueOf(id))
                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "PUT");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String getDangNhap() {
        Uri builtURI = Uri.parse(chuoi+"nhanvien").buildUpon().build();

        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "GET");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String getBan() {
        Uri builtURI = Uri.parse(chuoi+"ban").buildUpon()
                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "GET");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String TaoHoaDon(HoaDon hd) {
        Uri builtURI = Uri.parse(chuoi+"hoadon").buildUpon()
                .appendQueryParameter("TongTien",Double.toString(hd.getTongtien()))
                .appendQueryParameter("ThoiGianLap",hd.getThoigianlap())
                .appendQueryParameter("MaBan",Integer.toString(hd.getMaban()))
                .appendQueryParameter("MaNV",Integer.toString(hd.getManv()))
                .appendQueryParameter("TrangThai",Integer.toString(hd.getTrangThai()))
                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "POST");
        } catch (MalformedURLException e) {
            return null;
        }
    }
    public static String TaoChiTietHD(ThucDon td, int id) {
        Uri builtURI = Uri.parse(chuoi+"chitiethoadon").buildUpon()
                .appendQueryParameter("SoLuong",Integer.toString(td.getSoluong()))
                .appendQueryParameter("Gia",Double.toString(td.getGiatien()))
                .appendQueryParameter("MaHD",Integer.toString(id))
                .appendQueryParameter("MaMon",Integer.toString(td.getMamon()))
                .appendQueryParameter("DonGia",Double.toString(td.getDongia()))
                .build();
        try {
            URL requestURL = new URL(builtURI.toString());

            return callAPI(requestURL, "POST");
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
