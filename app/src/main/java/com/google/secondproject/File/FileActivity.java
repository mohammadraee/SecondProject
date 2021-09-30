package com.google.secondproject.File;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.secondproject.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileActivity extends AppCompatActivity {

    EditText fileName, fileContent;
    Button btnCreateFile, btnLoadFile;
    TextView fileTV;
    CheckBox checkBoxFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        initViews();
    }

    private void initViews() {
        fileName = findViewById(R.id.input_fileName);
        fileContent = findViewById(R.id.input_fileContent);
        btnLoadFile = findViewById(R.id.file_load_btn);
        btnCreateFile = findViewById(R.id.file_create_btn);
        fileTV = findViewById(R.id.file_textView);
        fileTV.setText("internal : " + getFilesDir().getAbsolutePath() + "\n");
        fileTV.append("external : " + Environment.getExternalStorageDirectory().getAbsolutePath());
        checkBoxFile = findViewById(R.id.file_checkBox);
    }

    public void onClick(View view) {
        if (checkBoxFile.isChecked()) {
            checkExternalStorage();
            return;
        }
        if (fileName.getText().toString().trim().isEmpty()) {
            fileName.setError("file name can't be empty");
            return;
        }
        String name = fileName.getText().toString().trim();

        if (view.getId() == R.id.file_create_btn) {
            name.replace(' ', '_');
            if (fileContent.getText().toString().trim().isEmpty()) {
                fileContent.setError("file content can't be empty");
                return;
            }
            String content = fileContent.getText().toString().trim();
            createFile(name, content);

        } else if (view.getId() == R.id.file_load_btn) {
            String getContent = readFile(name);
            fileContent.setText(getContent);
        }
    }

    private String readFile(String name) {
        try {
            FileInputStream fis = openFileInput(name);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line="";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            reader.close();
            isr.close();
            fis.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exception : " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
        return "";
    }

    private void createFile(String name, String content) {
        try {
            FileOutputStream fos = openFileOutput(name , MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Boolean checkExternalStorage() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            Toast.makeText(this, "external file is read-only!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "external file is not available!", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}