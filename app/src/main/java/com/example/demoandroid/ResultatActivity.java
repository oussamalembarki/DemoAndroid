package com.example.demoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Écran de résultat : affiche le résultat du calcul et rappelle l'opération.
 */
public class ResultatActivity extends AppCompatActivity {

    private TextView txtRes, txtOperation;
    private Button btnNav3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resultat);

        // Évite que le contenu passe sous les barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtRes = findViewById(R.id.txtRes);
        txtOperation = findViewById(R.id.txtOperation);
        btnNav3 = findViewById(R.id.btnNav3);

        // Récupération des données envoyées par CalculsActivity
        String resultat = getIntent().getStringExtra("res");
        String operation = getIntent().getStringExtra("operation");
        txtRes.setText(resultat);
        txtOperation.setText(operation);

        // Retour à la calculatrice
        btnNav3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), CalculsActivity.class);
            startActivity(intent);
        });
    }
}