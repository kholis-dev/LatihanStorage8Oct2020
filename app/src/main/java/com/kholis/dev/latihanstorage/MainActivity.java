package com.kholis.dev.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FILENAME = "namafile.txt";
    TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show = findViewById(R.id.textView);
        findViewById(R.id.btnCreate).setOnClickListener(this);
        findViewById(R.id.btnUpdate).setOnClickListener(this);
        findViewById(R.id.btnRead).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCreate:
                buatFile();
                break;
            case R.id.btnUpdate:
                ubahFile();
                break;
            case R.id.btnRead:
                bacaFile();
                break;
            case R.id.btnDelete:
                hapusFile();
                break;
        }
    }

    private void ubahFile() {
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            if(file.delete()){
                String isiFile = "ini isi setelah tombol di UPDATE by NURKHOLIS";
                FileOutputStream fileOutputStream = null;
                try {
                    file.createNewFile();

                    fileOutputStream = new FileOutputStream(file,true);
                    fileOutputStream.write(isiFile.getBytes());
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void hapusFile() {
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            if(file.delete()){
                Toast.makeText(this, "File dengan nama "+FILENAME+" berhasil dihapus", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Gagal menghapus file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void bacaFile() {
        File file = new File(getFilesDir(),FILENAME);
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            show.setText(text.toString());
        }
    }

    private void buatFile() {
        String isiFile = "ini isi untuk yang buat file awal by NURKHOLIS";
        File file = new File(getFilesDir(),FILENAME);
        FileOutputStream fileOutputStream = null;
        try {
            file.createNewFile();

            fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write(isiFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            Toast.makeText(this, "File berhasil dibuat", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}