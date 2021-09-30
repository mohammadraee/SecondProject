package com.google.secondproject.JsonXml.XmlPull;

import android.content.Context;

import com.google.secondproject.JsonXml.Element.Flower;
import com.google.secondproject.R;

import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    private Context context;
    private List<Flower> flowerList;
    private Flower currentFlower;
    private String currentTag;

    public XmlParser(Context context) {
        this.context = context;
    }

    public List<Flower> parseXml() {
        flowerList = new ArrayList<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            org.xmlpull.v1.XmlPullParser parser = factory.newPullParser();
            InputStream input = context.getResources().openRawResource(R.raw.flowers_xml);
            parser.setInput(input, null);
            int eventType = parser.getEventType();
            while (eventType != org.xmlpull.v1.XmlPullParser.END_DOCUMENT) {
                if (eventType == org.xmlpull.v1.XmlPullParser.START_TAG) {
                    handleStartTag(parser.getName());
                } else if (eventType == org.xmlpull.v1.XmlPullParser.TEXT) {
                    handleText(parser.getText());
                } else if (eventType == org.xmlpull.v1.XmlPullParser.END_TAG) {
                    currentTag = null;
                }
                eventType = parser.next();
            }
            input.close();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return flowerList;
    }

    private void handleStartTag(String tagName) {
        if ("product".equals(tagName)) {
            currentFlower = new Flower();
            flowerList.add(currentFlower);
        } else {
            currentTag = tagName;
        }
    }

    private void handleText(String text) {
        if (currentFlower == null || currentTag == null) return;
        switch (currentTag) {
            case "productId":
                currentFlower.setProductId(Long.parseLong(text));
                break;
            case "category":
                currentFlower.setCategory(text);
                break;
            case "name":
                currentFlower.setName(text);
                break;
            case "instructions":
                currentFlower.setInstructions(text);
                break;
            case "price":
                currentFlower.setPrice(Double.parseDouble(text));
                break;
            case "photo":
                currentFlower.setPhoto(text);
                break;
            default:
                break;
        }
    }
}