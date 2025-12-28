package com.example.dev;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class KayitOlActivity extends AppCompatActivity {

    EditText etMail, etPass;
    Button btnKaydet;
    VeritabaniYardimcisi dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        etMail = findViewById(R.id.etYeniKadi);
        etPass = findViewById(R.id.etYeniSifre);
        btnKaydet = findViewById(R.id.btnKaydet);
        dbHelper = new VeritabaniYardimcisi(this);

        btnKaydet.setOnClickListener(v -> {
            String mail = etMail.getText().toString().trim();
            String pass = etPass.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                Toast.makeText(this, "Email formatı hatalı!", Toast.LENGTH_SHORT).show();
            } else if (pass.length() < 4) {
                Toast.makeText(this, "Şifre en az 4 karakter olmalı!", Toast.LENGTH_SHORT).show();
            } else {
                if (dbHelper.kullaniciEkle(mail, pass)) {
                    Toast.makeText(this, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}