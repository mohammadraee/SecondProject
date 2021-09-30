package com.google.secondproject.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.google.secondproject.JsonXml.Element.Flower;

import java.util.ArrayList;
import java.util.List;

public class FlowerDBHelper extends SQLiteOpenHelper {

    public static final String LOGTAG = "flowerDatabase";
    private static final String DB_NAME = "flower_db";
    private static final int DB_VERSION = 1;
    private static final String FLOWER_TABLE_NAME = "tb_flower";
    private static final String[] columns = {Flower.KEY_ID, Flower.KEY_NAME, Flower.KEY_CAT,
            Flower.KEY_INS, Flower.KEY_PRICE, Flower.KEY_PHOTO};
    private static final String CMD = "CREATE TABLE IF NOT EXISTS '" + FLOWER_TABLE_NAME + "' ('" +
            Flower.KEY_ID + "' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,'" +
            Flower.KEY_NAME + "' TEXT ,'" +
            Flower.KEY_CAT + "' TEXT ,'" +
            Flower.KEY_INS + "' TEXT ,'" +
            Flower.KEY_PRICE + "' NAMERIC ,'" +
            Flower.KEY_PHOTO + "' TEXT )";

    public FlowerDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CMD);
        Log.i(LOGTAG, "table created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FLOWER_TABLE_NAME);
        Log.i(LOGTAG, "table dropped.");
        onCreate(db);
    }

    public void insert(Flower flower) {
        if (getFlowers(Flower.KEY_ID + " = " + flower.getProductId(), null).isEmpty()) {
            SQLiteDatabase db = getWritableDatabase();
            Long insertID = db.insert(FLOWER_TABLE_NAME, null, flower.getContentValues());
            if (insertID == -1) {
                Log.i(LOGTAG, "inserted failed. (" + flower.getName() + ")");
            } else {
                Log.i(LOGTAG, "data inserted with id : " + insertID);
            }
            if (db.isOpen()) db.close();
        }
    }

    public List<Flower> readAllFlower() {
        SQLiteDatabase db = getReadableDatabase();
        List<Flower> flowerList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM '" + FLOWER_TABLE_NAME + "'", null);
        if (cursor.moveToFirst()) {
            do {
                flowerList.add(Flower.cursorToFlower(cursor));
            } while (cursor.moveToNext());
        }
        Log.i(LOGTAG, cursor.getCount() + " item read from flowerTable");
        if (db.isOpen()) db.close();
        return flowerList;
    }

    public void delete(long productId) {
        SQLiteDatabase db = getWritableDatabase();
        int log = db.delete(FLOWER_TABLE_NAME, Flower.KEY_ID + " = ?", new String[]{String.valueOf(productId)});
        Log.i(LOGTAG, log + " rows was deleted.");
        if (db.isOpen()) db.close();
    }

    public void update(Long productId, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        int log = db.update(FLOWER_TABLE_NAME, values, Flower.KEY_ID + " = " + productId, null);
        Log.i(LOGTAG, log + " rows was update");
        if (db.isOpen()) db.close();
    }

    public List<Flower> getFlowers(String whereClause, String[] args) {
        SQLiteDatabase db = getReadableDatabase();
        List<Flower> flowerList = new ArrayList<>();
        Cursor cursor = db.query(FLOWER_TABLE_NAME, columns, whereClause, args, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                flowerList.add(Flower.cursorToFlower(cursor));
            } while (cursor.moveToNext());
        }
        if (db.isOpen()) db.close();
        return flowerList;
    }

    public List<Flower> getFlowers(String whereClause, String[] args, String orderBy) {
        SQLiteDatabase db = getReadableDatabase();
        List<Flower> flowerList = new ArrayList<>();
        Cursor cursor = db.query(FLOWER_TABLE_NAME, columns, whereClause, args, null, null, orderBy);
        if (cursor.moveToFirst()) {
            do {
                flowerList.add(Flower.cursorToFlower(cursor));
            } while (cursor.moveToNext());
        }
        if (db.isOpen()) db.close();
        return flowerList;
    }

}
