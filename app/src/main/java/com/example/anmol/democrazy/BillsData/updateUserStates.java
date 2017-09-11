package com.example.anmol.democrazy.BillsData;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anmol.democrazy.PhoneNoSave.SavePhoneNo;
import com.example.anmol.democrazy.login.LoginKey;


import java.util.HashMap;
import java.util.Map;


public class updateUserStates  {

    RequestQueue rq;
    Context ctx;

    private static final String URL = "http://139.59.86.83:4000/login/secure/billsOrdinances/updateStates";

    // Constructor : - Type
    public updateUserStates(Context ctx){
        this.ctx=ctx;
    }

    public void updateStates(){

        rq= Volley.newRequestQueue(ctx);

        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        System.out.println(response);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){

            // Passing Params
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                SavePhoneNo savePhoneNo=new SavePhoneNo(ctx);
                String phone=savePhoneNo.getPhoneNo();
                map.put("phone",phone);
                UserStates userStates=new UserStates(ctx);
                String s=userStates.getUserStates();
                map.put("SCId",s);
                return map;
            }

            //Passing Cookie
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String,String> map = new HashMap<>();
                LoginKey loginKey = new LoginKey(ctx);
                map.put("Cookie",loginKey.getLoginKey());
                return map;
            }
        };

        rq.add(stringRequest);

    }
}