package com.example.dev;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class NufusActivity extends AppCompatActivity {

    ListView lvNufus;
    String[] nufusVerileri = {
            "2020 Yılı: 32.8 Milyon",
            "2021 Yılı: 33.9 Milyon",
            "2022 Yılı: 35.0 Milyon",
            "2023 Yılı: 36.2 Milyon",
            "2024 Yılı: 37.4 Milyon",
            "2025 Yılı: 38.6 Milyon"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nufus);

        lvNufus = findViewById(R.id.lvNufus);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nufusVerileri);
        lvNufus.setAdapter(adapter);
    }
}