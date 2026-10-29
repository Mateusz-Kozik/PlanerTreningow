package com.example.planertreningow;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView ivCwiczenie;
    private TextView tvNazwaCwiczenia;
    private TextView tvLicznikSerii;
    private TextView tvLacznaLiczbaSerii;

    private final Random random = new Random();

    private final int[] obrazyCwiczen = {
            R.drawable.cwiczenie0,
            R.drawable.cwiczenie1,
            R.drawable.cwiczenie2,
            R.drawable.cwiczenie3,
            R.drawable.cwiczenie4,
            R.drawable.cwiczenie5,
            R.drawable.cwiczenie6
    };

    private final String[] nazwyCwiczen = {
            "Losuj ćwiczenie",
            "Przysiad",
            "Pompki",
            "Plank",
            "Wykroki",
            "Burpees",
            "Podciąganie"
    };

    private int liczbaSeriiAktualnegoCwiczenia = 0;
    private int lacznaLiczbaSerii = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivCwiczenie = findViewById(R.id.ivCwiczenie);
        tvNazwaCwiczenia = findViewById(R.id.tvNazwaCwiczenia);
        tvLicznikSerii = findViewById(R.id.tvLicznikSerii);
        tvLacznaLiczbaSerii = findViewById(R.id.tvLacznaLiczbaSerii);

        Button btnLosuj = findViewById(R.id.btnLosuj);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnNastepne = findViewById(R.id.btnNastepne);
        Button btnReset = findViewById(R.id.btnReset);

        btnLosuj.setOnClickListener(v -> losujCwiczenie());

        btnPlus.setOnClickListener(v -> {
            liczbaSeriiAktualnegoCwiczenia++;
            aktualizujLicznikSerii();
        });

        btnMinus.setOnClickListener(v -> {
            if (liczbaSeriiAktualnegoCwiczenia > 0) {
                liczbaSeriiAktualnegoCwiczenia--;
                aktualizujLicznikSerii();
            }
        });

        btnNastepne.setOnClickListener(v -> {
            lacznaLiczbaSerii += liczbaSeriiAktualnegoCwiczenia;
            aktualizujLacznaLiczbeSerii();
            losujCwiczenie();
        });

        btnReset.setOnClickListener(v -> resetujPlaner());
    }

    private void losujCwiczenie() {
        int numerCwiczenia = random.nextInt(6) + 1;

        ivCwiczenie.setImageResource(obrazyCwiczen[numerCwiczenia]);
        tvNazwaCwiczenia.setText(nazwyCwiczen[numerCwiczenia]);

        liczbaSeriiAktualnegoCwiczenia = 0;
        aktualizujLicznikSerii();
    }

    private void aktualizujLicznikSerii() {
        tvLicznikSerii.setText(String.valueOf(liczbaSeriiAktualnegoCwiczenia));
    }

    private void aktualizujLacznaLiczbeSerii() {
        tvLacznaLiczbaSerii.setText("Łączna liczba serii: " + lacznaLiczbaSerii);
    }

    private void resetujPlaner() {
        liczbaSeriiAktualnegoCwiczenia = 0;
        lacznaLiczbaSerii = 0;

        ivCwiczenie.setImageResource(obrazyCwiczen[0]);
        tvNazwaCwiczenia.setText(nazwyCwiczen[0]);

        aktualizujLicznikSerii();
        aktualizujLacznaLiczbeSerii();
    }
}
