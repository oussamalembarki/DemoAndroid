package com.example.demoandroid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

/**
 * Écran de calcul : saisie de deux nombres et choix d'une opération.
 */
public class CalculsActivity extends AppCompatActivity {

    private EditText op1, op2;
    private TextInputLayout tilOp1, tilOp2;
    private Button btnA, btnS, btnM, btnD, btnNav2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculs);

        // Évite que le contenu passe sous les barres système
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Liaison des vues
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        tilOp1 = findViewById(R.id.tilOp1);
        tilOp2 = findViewById(R.id.tilOp2);
        btnA = findViewById(R.id.btnA);
        btnS = findViewById(R.id.btnS);
        btnM = findViewById(R.id.btnM);
        btnD = findViewById(R.id.btnD);
        btnNav2 = findViewById(R.id.btnNav2);

        // Chaque bouton lance le calcul avec son opérateur
        btnA.setOnClickListener(view -> calculer('+'));
        btnS.setOnClickListener(view -> calculer('-'));
        btnM.setOnClickListener(view -> calculer('*'));
        btnD.setOnClickListener(view -> calculer('/'));

        // Navigation vers l'écran d'accueil
        btnNav2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Vérifie les saisies, effectue l'opération demandée
     * puis ouvre l'écran de résultat.
     */
    private void calculer(char operateur) {
        // Réinitialise les anciens messages d'erreur
        tilOp1.setError(null);
        tilOp2.setError(null);

        String texte1 = op1.getText().toString().trim();
        String texte2 = op2.getText().toString().trim();

        // Vérification : champs vides
        boolean champsValides = true;
        if (texte1.isEmpty()) {
            tilOp1.setError(getString(R.string.erreur_champ_vide));
            champsValides = false;
        }
        if (texte2.isEmpty()) {
            tilOp2.setError(getString(R.string.erreur_champ_vide));
            champsValides = false;
        }
        if (!champsValides) {
            return;
        }

        // Conversion en entiers (protégée contre les nombres trop grands ou le signe seul)
        int a;
        int b;
        try {
            a = Integer.parseInt(texte1);
            b = Integer.parseInt(texte2);
        } catch (NumberFormatException e) {
            tilOp1.setError(getString(R.string.erreur_nombre_invalide));
            tilOp2.setError(getString(R.string.erreur_nombre_invalide));
            return;
        }

        // Calcul selon l'opérateur choisi
        String resultat;
        String symbole;
        switch (operateur) {
            case '+':
                resultat = String.valueOf(a + b);
                symbole = getString(R.string.symbole_addition);
                break;
            case '-':
                resultat = String.valueOf(a - b);
                symbole = getString(R.string.symbole_soustraction);
                break;
            case '*':
                resultat = String.valueOf(a * b);
                symbole = getString(R.string.symbole_multiplication);
                break;
            default: // '/'
                if (b == 0) {
                    tilOp2.setError(getString(R.string.erreur_division_zero));
                    return;
                }
                resultat = String.valueOf((double) a / b);
                symbole = getString(R.string.symbole_division);
                break;
        }

        // Envoi du résultat et du rappel de l'opération à l'écran suivant
        Intent intent = new Intent(getApplicationContext(), ResultatActivity.class);
        intent.putExtra("res", resultat);
        intent.putExtra("operation", a + " " + symbole + " " + b + " =");
        startActivity(intent);
    }
}