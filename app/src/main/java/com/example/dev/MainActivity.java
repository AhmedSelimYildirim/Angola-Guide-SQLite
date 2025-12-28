package com.example.dev;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etSifre;
    Button btnGiris, btnKayitOl, btnKayitSil;
    VeritabaniYardimcisi dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etKullaniciAdi);
        etSifre = findViewById(R.id.etSifre);
        btnGiris = findViewById(R.id.btnGiris);
        btnKayitOl = findViewById(R.id.btnKayitOlSayfasinaGit);
        btnKayitSil = findViewById(R.id.btnKayitSilGit);

        dbHelper = new VeritabaniYardimcisi(this);

        btnGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = etEmail.getText().toString().trim();
                String pass = etSifre.getText().toString().trim();

                if (mail.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Lütfen email ve şifre girin", Toast.LENGTH_SHORT).show();
                } else if (mail.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Lütfen email girin", Toast.LENGTH_SHORT).show();
                } else if (pass.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Lütfen şifre girin", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    Toast.makeText(MainActivity.this, "Geçersiz email formatı!", Toast.LENGTH_SHORT).show();
                } else {
                    if (dbHelper.girisKontrol(mail, pass)) {
                        Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                        intent.putExtra("userEmail", mail);
                        intent.putExtra("adminYetkisi", mail.equals("admin@test.com") || mail.equals("selim@test.com"));
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Hatalı şifre veya kullanıcı!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnKayitOl.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, KayitOlActivity.class)));
        btnKayitSil.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, KayitSilActivity.class)));
    }
}