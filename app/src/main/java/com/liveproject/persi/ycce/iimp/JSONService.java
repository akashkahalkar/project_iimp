package com.liveproject.persi.ycce.iimp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tiger on 28-08-2016.
 */
public class JSONService {

    public static String[] ids;
    public static String[] names;
    public static String[] emails;

    public static final String JSON_ARRAY = "attributes";
    public static final String KEY_ID = "Id";
    public static final String KEY_NAME = "FirstName";
    public static final String KEY_EMAIL = "Email";

    private JSONArray users = null;

    private String json;

    public JSONService(String json) {
        this.json = json;
    }

    protected void parseJSON() {
        JSONObject jsonObject = null;
        JSONArray jarray = null;
        try {
            //  jsonObject = new JSONObject(json);
            //  users = jsonObject.getJSONArray(JSON_ARRAY);

            jarray = new JSONArray(json);

            ids = new String[jarray.length()];
            names = new String[jarray.length()];
            emails = new String[jarray.length()];

            for (int j = 0; j < jarray.length(); j++) {

                JSONObject jo = jarray.getJSONObject(j);
                ids[j] = jo.getString(KEY_ID);
                names[j] = jo.getString(KEY_NAME);
                emails[j] = jo.getString(KEY_EMAIL);
            }

            /*for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                ids[i] = jo.getString(KEY_ID);
                names[i] = jo.getString(KEY_NAME);
                emails[i] = jo.getString(KEY_EMAIL);
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}