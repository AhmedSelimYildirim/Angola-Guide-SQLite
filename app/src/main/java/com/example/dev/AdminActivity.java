package com.example.dev;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    EditText etSehirAdi, etNufus, etAciklama;
    Button btnEkle, btnSil, btnGuncelle;
    VeritabaniYardimcisi dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        etSehirAdi = findViewById(R.id.etSehirAdi);
        etNufus = findViewById(R.id.etNufus);
        etAciklama = findViewById(R.id.etAciklama);
        btnEkle = findViewById(R.id.btnEkle);
        btnSil = findViewById(R.id.btnSil);
        btnGuncelle = findViewById(R.id.btnGuncelle);

        dbHelper = new VeritabaniYardimcisi(this);

        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad = etSehirAdi.getText().toString();
                String nufus = etNufus.getText().toString();
                String aciklama = etAciklama.getText().toString();

                if (ad.isEmpty() || nufus.isEmpty()) {
                    Toast.makeText(AdminActivity.this, "Şehir Adı ve Nüfus boş olamaz!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean sonuc = dbHelper.sehirEkle(ad, nufus, aciklama);
                    if (sonuc) {
                        Toast.makeText(AdminActivity.this, "Şehir Eklendi!", Toast.LENGTH_SHORT).show();
                        temizle();
                    } else {
                        Toast.makeText(AdminActivity.this, "Hata oluştu!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad = etSehirAdi.getText().toString();
                if (ad.isEmpty()) {
                    Toast.makeText(AdminActivity.this, "Silmek için Şehir Adını yazın!", Toast.LENGTH_SHORT).show();
                } else {
                    Integer silinenSatir = dbHelper.sehirSil(ad);
                    if (silinenSatir > 0) {
                        Toast.makeText(AdminActivity.this, "Şehir Silindi!", Toast.LENGTH_SHORT).show();
                        temizle();
                    } else {
                        Toast.makeText(AdminActivity.this, "Bu isimde şehir bulunamadı.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ad = etSehirAdi.getText().toString();
                String yeniNufus = etNufus.getText().toString();
                String yeniAciklama = etAciklama.getText().toString();

                if (ad.isEmpty()) {
                    Toast.makeText(AdminActivity.this, "Güncellemek için Şehir Adını yazın!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean sonuc = dbHelper.sehirGuncelle(ad, yeniNufus, yeniAciklama);
                    if (sonuc) {
                        Toast.makeText(AdminActivity.this, "Bilgiler Güncellendi!", Toast.LENGTH_SHORT).show();
                        temizle();
                    } else {
                        Toast.makeText(AdminActivity.this, "Güncelleme Başarısız (Şehir ismi doğru mu?)", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    void temizle() {
        etSehirAdi.setText("");
        etNufus.setText("");
        etAciklama.setText("");
    }
}