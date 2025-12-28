package com.example.dev;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class KayitSilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_sil);

        EditText mail = findViewById(R.id.etSilMail);
        EditText pass = findViewById(R.id.etSilPass);
        Button btn = findViewById(R.id.btnSilOnayla);
        VeritabaniYardimcisi db = new VeritabaniYardimcisi(this);

        btn.setOnClickListener(v -> {
            if (db.kullaniciSil(mail.getText().toString(), pass.getText().toString())) {
                Toast.makeText(this, "Hesap Silindi!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Hatalı bilgiler!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}