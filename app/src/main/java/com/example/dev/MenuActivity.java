package com.example.dev;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class MenuActivity extends AppCompatActivity {

    ViewPager2 viewPagerSlider;
    Button btnSolOk, btnSagOk;
    Button btnGenelBilgi, btnNufus, btnSehirler, btnTuristik, btnAdmin, btnAyarlar;

    int[] resimler = {R.drawable.img, R.drawable.img_1, R.drawable.img_2};

    Handler handler = new Handler();
    Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = viewPagerSlider.getCurrentItem();
            int nextItem = currentItem + 1;
            if (nextItem >= resimler.length) {
                nextItem = 0;
            }
            viewPagerSlider.setCurrentItem(nextItem, true);
            handler.postDelayed(this, 3000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String userEmail = getIntent().getStringExtra("userEmail");
        boolean isAdmin = getIntent().getBooleanExtra("adminYetkisi", false);

        viewPagerSlider = findViewById(R.id.viewPagerSlider);
        btnSolOk = findViewById(R.id.btnSolOk);
        btnSagOk = findViewById(R.id.btnSagOk);

        btnGenelBilgi = findViewById(R.id.btnGenelBilgi);
        btnNufus = findViewById(R.id.btnNufus);
        btnSehirler = findViewById(R.id.btnSehirler);
        btnTuristik = findViewById(R.id.btnTuristik);
        btnAdmin = findViewById(R.id.btnAdmin);
        btnAyarlar = findViewById(R.id.btnAyarlar);

        if (isAdmin) {
            btnAdmin.setVisibility(View.VISIBLE);
        } else {
            btnAdmin.setVisibility(View.GONE);
        }

        SliderAdapter adapter = new SliderAdapter(resimler);
        viewPagerSlider.setAdapter(adapter);

        handler.postDelayed(sliderRunnable, 3000);

        btnSolOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPagerSlider.getCurrentItem();
                if (current > 0) {
                    viewPagerSlider.setCurrentItem(current - 1, true);
                } else {
                    viewPagerSlider.setCurrentItem(resimler.length - 1, true);
                }
            }
        });

        btnSagOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = viewPagerSlider.getCurrentItem();
                if (current < resimler.length - 1) {
                    viewPagerSlider.setCurrentItem(current + 1, true);
                } else {
                    viewPagerSlider.setCurrentItem(0, true);
                }
            }
        });

        btnGenelBilgi.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, GenelBilgiActivity.class)));
        btnNufus.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, NufusActivity.class)));
        btnSehirler.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, SehirlerActivity.class)));
        btnTuristik.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, TuristikActivity.class)));
        btnAdmin.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, AdminActivity.class)));

        btnAyarlar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, SettingsActivity.class);
                intent.putExtra("userEmail", userEmail);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(sliderRunnable, 3000);
    }
}