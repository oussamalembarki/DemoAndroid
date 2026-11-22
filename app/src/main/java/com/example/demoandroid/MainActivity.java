package com.example.demoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Écran d'accueil : permet d'accéder à la calculatrice.
 */
public class MainActivity extends AppCompatActivity {

    private Button btnNav1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Évite que le contenu passe sous les barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnNav1 = findViewById(R.id.btnNav1);

        // Navigation vers l'écran de calcul
        btnNav1.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CalculsActivity.class);
            startActivity(intent);
        });
    }
}