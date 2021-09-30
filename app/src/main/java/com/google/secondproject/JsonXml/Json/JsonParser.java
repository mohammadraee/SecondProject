package com.google.secondproject.JsonXml.Json;

import com.google.secondproject.JsonXml.Element.Flower;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    public String convertInputStreamToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            while (bis.available() != 0) {
                sb.append((char) bis.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public List<Flower> parseJson(InputStream inputStream) {
        String jsonString = convertInputStreamToString(inputStream);
        List<Flower> flowerList = new ArrayList<>();
        try {
            JSONArray json = new JSONArray(jsonString);
            for (int i = 0; i < json.length(); i++) {
                JSONObject object = json.getJSONObject(i);
                Flower flower = new Flower();
                flower.setProductId(object.getLong("productId"));
                flower.setPrice(object.getDouble("price"));
                flower.setCategory(object.getString("category"));
                flower.setInstructions(object.getString("instructions"));
                flower.setName(object.getString("name"));
                flower.setPhoto(object.getString("photo"));
                flowerList.add(flower);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return flowerList;
    }
}
