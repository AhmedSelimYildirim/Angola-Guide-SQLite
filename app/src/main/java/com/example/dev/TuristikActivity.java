package com.example.dev;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TuristikActivity extends AppCompatActivity {

    LinearLayout container;
    String[] yerler = {"• Kalandula Şelaleleri", "• Quiçama Ulusal Parkı", "• Luanda Kalesi", "• Miradouro da Lua", "• Mussulo Adası", "• Iron Palace"};
    String[] bilgiler = {
            "Afrika'nın en büyük şelalelerinden biridir.",
            "Angola'nın en ünlü milli parkı ve vahşi yaşam alanıdır.",
            "1576'da inşa edilmiş tarihi savunma kalesi.",
            "Ay yüzeyine benzeyen muazzam bir uçurum manzarası.",
            "Beyaz kumları ve palmiye ağaçlarıyla ünlü ada.",
            "Gustave Eiffel tarafından tasarlanan tamamen demir yapı."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turistik);

        container = findViewById(R.id.containerTuristik);

        for (int i = 0; i < yerler.length; i++) {
            addExpandableItem(yerler[i], bilgiler[i]);
        }
    }

    private void addExpandableItem(String baslik, String icerik) {
        TextView tvBaslik = new TextView(this);
        tvBaslik.setText(baslik);
        tvBaslik.setTextSize(18);
        tvBaslik.setPadding(20, 30, 20, 30);
        tvBaslik.setTextColor(Color.BLACK);
        tvBaslik.setBackgroundResource(android.R.drawable.edit_text);

        TextView tvIcerik = new TextView(this);
        tvIcerik.setText(icerik);
        tvIcerik.setTextSize(16);
        tvIcerik.setPadding(40, 20, 20, 20);
        tvIcerik.setTextColor(Color.DKGRAY);
        tvIcerik.setVisibility(View.GONE);

        tvBaslik.setOnClickListener(v -> {
            if (tvIcerik.getVisibility() == View.GONE) {
                tvIcerik.setVisibility(View.VISIBLE);
            } else {
                tvIcerik.setVisibility(View.GONE);
            }
        });

        container.addView(tvBaslik);
        container.addView(tvIcerik);
    }
}