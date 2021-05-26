package com.example.mapremireapplicationavecalexandrequivatresuperbe;


import android.util.Log;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class JsonUtils {

    public static JsonObject mapToJSON(Map<String, Object> params) {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                jsonObj_.put(entry.getKey(), entry.getValue());
            }
            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

            //print parameter
            Log.d("MY gson.JSON:  ", "AS PARAMETER  " + gsonObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return gsonObject;
    }


}

