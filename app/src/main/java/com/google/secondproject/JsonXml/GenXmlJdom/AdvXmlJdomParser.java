package com.google.secondproject.JsonXml.GenXmlJdom;

import com.google.secondproject.JsonXml.Element.Flower;

import org.jdom2.Element;

import java.io.InputStream;

public class AdvXmlJdomParser extends GenericXmlParser<Flower> {

    InputStream input;
    public AdvXmlJdomParser(InputStream input){
        this.input = input;
    }


    @Override
    public InputStream getInput() {
        return input;
    }

    @Override
    public String getObjectNodesKey() {
        return "product";
    }

    @Override
    public Flower getObjectFromNode(Element node) {
        Flower flower = new Flower();
        flower.setName(node.getChildText("name"));
        flower.setProductId(Long.valueOf(node.getChildText("productId")));
        flower.setCategory(node.getChildText("category"));
        flower.setInstructions(node.getChildText("instructions"));
        flower.setPhoto(node.getChildText("photo"));
        flower.setPrice(Double.valueOf(node.getChildText("price")));
        return flower;
    }
}
