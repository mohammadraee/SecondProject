package com.google.secondproject.JsonXml.SimXmlJdom;

import android.content.Context;

import com.google.secondproject.JsonXml.Element.Flower;
import com.google.secondproject.R;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SimXmlJdomParser {
    private Context context;
    private List<Flower> flowerList;

    public SimXmlJdomParser(Context context) {
        this.context = context;
    }

    public List<Flower> parseXml() {
        flowerList = new ArrayList<>();
        try {
            InputStream input = context.getResources().openRawResource(R.raw.flowers_xml);
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(input);
            Element rootNode = document.getRootElement();
            List<Element> nodes = rootNode.getChildren("product");
            for (Element node : nodes) {
                Flower flower = new Flower();
                flower.setPhoto(node.getChildText("photo"));
                flower.setName(node.getChildText("name"));
                flower.setInstructions(node.getChildText("instructions"));
                flower.setCategory(node.getChildText("category"));
                flower.setPrice(Double.valueOf(node.getChildText("price")));
                flower.setProductId(Long.valueOf(node.getChildText("productId")));
                flowerList.add(flower);
            }
            input.close();
        } catch (JDOMException | IOException e) {
            e.printStackTrace();
        }
        return flowerList;
    }

}
