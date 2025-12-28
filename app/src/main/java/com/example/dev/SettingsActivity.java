package com.example.dev;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class SettingsActivity extends AppCompatActivity {

    EditText etNewMail, etNewPass;
    Button btnUpdate;
    Switch swTheme;
    VeritabaniYardimcisi dbHelper;
    String currentEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        currentEmail = getIntent().getStringExtra("userEmail");
        etNewMail = findViewById(R.id.etNewEmail);
        etNewPass = findViewById(R.id.etNewPass);
        btnUpdate = findViewById(R.id.btnUpdateInfo);
        swTheme = findViewById(R.id.swTheme);
        dbHelper = new VeritabaniYardimcisi(this);

        etNewMail.setText(currentEmail);

        btnUpdate.setOnClickListener(v -> {
            String nMail = etNewMail.getText().toString().trim();
            String nPass = etNewPass.getText().toString().trim();

            if (Patterns.EMAIL_ADDRESS.matcher(nMail).matches() && !nPass.isEmpty()) {
                if (dbHelper.kullaniciGuncelle(currentEmail, nMail, nPass)) {
                    Toast.makeText(this, "Bilgiler Güncellendi!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            } else {
                Toast.makeText(this, "Geçersiz giriş!", Toast.LENGTH_SHORT).show();
            }
        });

        swTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
    }
}