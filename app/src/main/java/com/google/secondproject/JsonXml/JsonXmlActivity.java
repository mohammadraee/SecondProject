package com.google.secondproject.JsonXml;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.secondproject.JsonXml.ConvXmlToJson.ConvertXmlToJson;
import com.google.secondproject.JsonXml.ConvXmlToJson.Tour;
import com.google.secondproject.JsonXml.CustView.FlowerAdapter;
import com.google.secondproject.JsonXml.GenXmlJdom.AdvXmlJdomParser;
import com.google.secondproject.JsonXml.Json.JsonParser;
import com.google.secondproject.JsonXml.SimXmlJdom.SimXmlJdomParser;
import com.google.secondproject.JsonXml.Element.Flower;
import com.google.secondproject.JsonXml.XmlPull.XmlParser;
import com.google.secondproject.R;

import org.json.JSONArray;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonXmlActivity extends AppCompatActivity {

    Button btn_json_simple, btn_xml_to_json, btn_xml_PullParser, btn_xml_simpleJdom, btn_xml_genericJdom;
    ListView listView_jsonXml;
    List<Flower> flowers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_xml);
        btn_json_simple = findViewById(R.id.btn_json_simple);
        btn_xml_to_json = findViewById(R.id.btn_xml_to_json);
        btn_xml_PullParser = findViewById(R.id.btn_xml_pullParser);
        btn_xml_simpleJdom = findViewById(R.id.btn_xml_simpleJdom);
        btn_xml_genericJdom = findViewById(R.id.btn_xml_genericJdom);
        listView_jsonXml = findViewById(R.id.json_xml_listVIew);

        btn_xml_PullParser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowers = new XmlParser(JsonXmlActivity.this).parseXml();
                Toast.makeText(JsonXmlActivity.this, "Xml Pull Parser : Returned " + flowers.size() + " Items.",
                        Toast.LENGTH_LONG).show();
                refreshDisplay();
            }
        });

        btn_xml_simpleJdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flowers = new SimXmlJdomParser(JsonXmlActivity.this).parseXml();
                Toast.makeText(JsonXmlActivity.this, "Simple Xml Jdom : Returned " + flowers.size() + " Items.",
                        Toast.LENGTH_LONG).show();
                refreshDisplay();
            }
        });

        btn_xml_genericJdom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream inputStream = getResources().openRawResource(R.raw.flowers_xml);
                flowers = new AdvXmlJdomParser(inputStream).parseXml();
                Toast.makeText(JsonXmlActivity.this, "Generic Xml Jdom : Returned " + flowers.size() + " Items.",
                        Toast.LENGTH_LONG).show();
                refreshDisplay();
            }
        });

        btn_json_simple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputStream inputStream = getResources().openRawResource(R.raw.flowers_json);
                flowers = new JsonParser().parseJson(inputStream);
                Toast.makeText(JsonXmlActivity.this, "Simple Json : Returned " + flowers.size() + " Items.",
                        Toast.LENGTH_LONG).show();
                refreshDisplay();
            }
        });
        btn_xml_to_json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Tour> tourList;
                InputStream inputStream = getResources().openRawResource(R.raw.tours);
                tourList = new ConvertXmlToJson(inputStream).parseXml();
                Toast.makeText(JsonXmlActivity.this, "xml : Returned " + tourList.size() + " Items.",
                        Toast.LENGTH_LONG).show();
                ArrayAdapter<Tour> adapter = new ArrayAdapter<Tour>(JsonXmlActivity.this, android.R.layout.simple_list_item_1, tourList);
                listView_jsonXml.setAdapter(adapter);
                // create tours.json
                JSONArray json = ConvertXmlToJson.tourListToJsonArray(tourList);
                try{
                    FileOutputStream fos = openFileOutput("tours.json", MODE_PRIVATE);
                    fos.write(json.toString().getBytes());
                    fos.close();
                } catch (IOException e) {e.printStackTrace();}
            }
        });
    }

    public void refreshDisplay() {
        if (flowers == null) {
            flowers = new ArrayList<>();
        }
        FlowerAdapter adapter = new FlowerAdapter(this, flowers);
//        ArrayAdapter<Flower> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, flowers);
        listView_jsonXml.setAdapter(adapter);
    }
}