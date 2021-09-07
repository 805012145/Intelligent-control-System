package com.example.intelligentcontrolsystem.util;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.util.List;

public class JSONUtil {
    public static JSONObject SetJson(List<String> keys, List<Object> values) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        for (int i = 0; i < keys.size(); i++) {
            jsonObject.put(keys.get(i), values.get(i));
        }
        return jsonObject;
    }
}
