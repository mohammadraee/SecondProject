package com.google.secondproject.JsonXml.ConvXmlToJson;

import com.google.secondproject.JsonXml.GenXmlJdom.GenericXmlParser;

import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

public class ConvertXmlToJson extends GenericXmlParser<Tour> {

    InputStream input;

    public ConvertXmlToJson(InputStream input) {
        this.input = input;
    }

    @Override
    public InputStream getInput() {
        return input;
    }

    @Override
    public String getObjectNodesKey() {
        return "tour";
    }

    @Override
    public Tour getObjectFromNode(Element node) {
        Tour tour = new Tour();
        tour.setDescription(node.getChildText("description"));
        tour.setDifficulty(node.getChildText("difficulty"));
        tour.setImage(node.getChildText("image"));
        tour.setLength(node.getChildText("length"));
        tour.setLink(node.getChildText("link"));
        tour.setPackageTitle(node.getChildText("packageTitle"));
        tour.setPrice(node.getChildText("price"));
        tour.setTourId(node.getChildText("tourId"));
        tour.setTourTitle(node.getChildText("tourTitle"));
        return tour;
    }

    public static JSONArray tourListToJsonArray(List<Tour> tourList) {
        JSONArray json = new JSONArray();
        try {
            for (Tour tour : tourList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("tourId", tour.getTourId());
                jsonObject.put("tourTitle", tour.getTourTitle());
                jsonObject.put("packageTitle", tour.getPackageTitle());
                jsonObject.put("length", tour.getLength());
                jsonObject.put("link", tour.getLink());
                jsonObject.put("image", tour.getImage());
                jsonObject.put("difficulty", tour.getDifficulty());
                jsonObject.put("description", tour.getDescription());
                jsonObject.put("price", tour.getPrice());
                json.put(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
