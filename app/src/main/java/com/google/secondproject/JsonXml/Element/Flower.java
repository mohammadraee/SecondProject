package com.google.secondproject.JsonXml.Element;

import android.content.ContentValues;
import android.database.Cursor;

public class Flower {

    public static String KEY_ID = "productId";
    public static String KEY_CAT = "category";
    public static String KEY_NAME = "name";
    public static String KEY_INS = "instructions";
    public static String KEY_PRICE = "price";
    public static String KEY_PHOTO = "photo";

    private long productId;
    private String category;
    private String name;
    private String instructions;
    private double price;
    private String photo;

    public Flower() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return name + "\n$ " + price;
    }

    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(Flower.KEY_ID,productId);
        values.put(Flower.KEY_NAME, name);
        values.put(Flower.KEY_CAT, category);
        values.put(Flower.KEY_PRICE, price);
        values.put(Flower.KEY_INS, instructions);
        values.put(Flower.KEY_PHOTO,photo);
        return values;
    }

    public static Flower cursorToFlower(Cursor cursor){
        Flower flower = new Flower();
        flower.setProductId(cursor.getLong(cursor.getColumnIndex(Flower.KEY_ID)));
        flower.setName(cursor.getString(cursor.getColumnIndex(Flower.KEY_NAME)));
        flower.setCategory(cursor.getString(cursor.getColumnIndex(Flower.KEY_CAT)));
        flower.setInstructions(cursor.getString(cursor.getColumnIndex(Flower.KEY_INS)));
        flower.setPrice(cursor.getDouble(cursor.getColumnIndex(Flower.KEY_PRICE)));
        flower.setPhoto(cursor.getString(cursor.getColumnIndex(Flower.KEY_PHOTO)));
        return flower;
    }
}
