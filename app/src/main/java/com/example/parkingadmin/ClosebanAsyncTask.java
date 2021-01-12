package com.example.parkingadmin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class ClosebanAsyncTask extends AsyncTask<Integer,Void,String> {
    private Context context;
    public ClosebanAsyncTask(Context con) {
        this.context=con;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String ketqua=jsonObject.getString("result");
            if(ketqua.equals("success"))
            {
                Intent intent =new Intent(context,activity_ban.class);
                context.startActivity(intent);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected String doInBackground(Integer... integers) {
        return NetworkUnit.CloseBan(integers[0]);
    }
}
