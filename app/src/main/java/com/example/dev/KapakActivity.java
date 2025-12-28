package com.example.dev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class KapakActivity extends AppCompatActivity {

    Button btnKapakGiris;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kapak);

        btnKapakGiris = findViewById(R.id.btnKapakGiris);

        btnKapakGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KapakActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}