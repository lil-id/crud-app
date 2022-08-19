package com.master.latihandatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etNim;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etNama);
        etNim = findViewById(R.id.etNIM);

        insert = findViewById(R.id.buttonTambah);
        update = findViewById(R.id.buttonUbah);
        delete = findViewById(R.id.buttonHapus);
        view = findViewById(R.id.buttonDetail);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaTXT = etName.getText().toString();
                String nimTXT = etNim.getText().toString();

                if (namaTXT.equals("") || nimTXT.equals("")) {
                    Toast.makeText(MainActivity.this, "Mohon isi terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkInsert = DB.insertData(namaTXT, nimTXT);
                    if (checkInsert == true) {
                        Toast.makeText(MainActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data tidak dapat ditambahkan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaTXT = etName.getText().toString();
                String nimTXT = etNim.getText().toString();

                if (namaTXT.equals("") || nimTXT.equals("")) {
                    Toast.makeText(MainActivity.this, "Mohon isi terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUpdate = DB.updateData(namaTXT, nimTXT);
                    if (checkUpdate == true) {
                        Toast.makeText(MainActivity.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "NIM tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String namaTXT = etName.getText().toString();
                String nimTXT = etNim.getText().toString();

                if (namaTXT.equals("") || nimTXT.equals("")) {
                    Toast.makeText(MainActivity.this, "Mohon isi terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkDelete = DB.deleteData(namaTXT);
                    if (checkDelete == true) {
                        Toast.makeText(MainActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Data tidak dapat dihapus", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = DB.getData();
                if (cursor.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Tidak ada data.", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    buffer.append("Name : " +cursor.getString(0)+"\n");
                    buffer.append("NIM : " +cursor.getString(1)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Data Mahasiswa");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}