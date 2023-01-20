package com.example.fcm;

import android.content.Context;
import android.os.StrictMode;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class FcmSend {
    private  static String BASE_Url="https://fcm.googleapis.com/fcm/send";
    private static String Server_key = "key=AAAAboAhGYI:APA91bHY08Ko8-iD_qSQYa7-YFHpnFGrTJi2bTpTW8Laxr_9I8x4D_vAbB7JXWEWjQi7FpvK8cvOsroKmBT9AaRR_nG2dKuR5dxC1Y0DXSC_OYBAurrq1Z5ooAyJT2JmJ3zA-H9XO3vI";

    public static void pushNotification(Context context,String token,String title,String message){
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        RequestQueue queue  = Volley.newRequestQueue(context);
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("to",token);
            JSONObject notification = new JSONObject();
            notification.put("title",title);
            notification.put("body",message);
            jsonObject.put("notification",notification);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, BASE_Url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("FCM" + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                  Map<String,String> params = new HashMap<>();
                  params.put("content-Type","application/json");
                  params.put("Authorization",Server_key);
                  return params;
                }
            };

            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
