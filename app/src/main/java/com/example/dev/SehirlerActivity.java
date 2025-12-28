package com.example.dev;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SehirlerActivity extends AppCompatActivity {

    TableLayout tabloSehirler;
    VeritabaniYardimcisi dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehirler);

        tabloSehirler = findViewById(R.id.tabloSehirler);
        dbHelper = new VeritabaniYardimcisi(this);

        verileriListele();
    }

    private void verileriListele() {
        Cursor cursor = dbHelper.tumSehirleriGetir();

        if (cursor.getCount() == 0) {
            dbHelper.sehirEkle("Luanda", "2.5 Milyon", "Başkent ve ana liman bölgesidir.");
            dbHelper.sehirEkle("Huambo", "1.2 Milyon", "Tarımsal ve endüstriyel merkezdir.");
            dbHelper.sehirEkle("Lobito", "800 Bin", "Stratejik demiryolu şehridir.");
            cursor = dbHelper.tumSehirleriGetir();
        }

        while (cursor.moveToNext()) {
            String sehirAdi = cursor.getString(1);
            String nufus = cursor.getString(2);
            String aciklama = cursor.getString(3);

            TableRow satir = new TableRow(this);
            satir.setPadding(0, 10, 0, 10);
            satir.setBackgroundResource(android.R.drawable.edit_text);

            TextView tvSehir = new TextView(this);
            tvSehir.setText(sehirAdi);
            tvSehir.setTextSize(16);
            tvSehir.setTextColor(Color.BLUE);
            tvSehir.setPadding(15, 10, 0, 10);
            satir.addView(tvSehir);

            TextView tvNufus = new TextView(this);
            tvNufus.setText(nufus);
            tvNufus.setTextSize(16);
            tvNufus.setGravity(Gravity.CENTER);
            satir.addView(tvNufus);

            TextView tvAciklama = new TextView(this);
            tvAciklama.setText(aciklama);
            tvAciklama.setPadding(30, 10, 10, 10);
            tvAciklama.setVisibility(View.GONE);
            tvAciklama.setBackgroundColor(Color.parseColor("#EEEEEE"));

            satir.setOnClickListener(v -> {
                if (tvAciklama.getVisibility() == View.GONE) {
                    tvAciklama.setVisibility(View.VISIBLE);
                } else {
                    tvAciklama.setVisibility(View.GONE);
                }
            });

            tabloSehirler.addView(satir);
            tabloSehirler.addView(tvAciklama);
        }
    }
}