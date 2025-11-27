package com.example.basketballscoreapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.basketballscoreapp.databinding.ActivityResultadoBinding;

public class resultado extends AppCompatActivity {

    private ActivityResultadoBinding binding;
    private String Nlocal;
    private String NVisitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // ViewBinding
        binding = ActivityResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ️ Aquí cogemos los datos del intent
        Intent intent = getIntent();
        Nlocal = intent.getStringExtra("Nlocal");
        NVisitante = intent.getStringExtra("NVisitante");

        ActualizarResultado();
        cambiarTextos();
        volver();
    }

    private void ActualizarResultado() {
        Intent intent = getIntent();
        int puntajeLocal = intent.getIntExtra("local", 0);
        int puntajeVisitante = intent.getIntExtra("visitante", 0);

        binding.resultadoLocal.setText(String.valueOf(puntajeLocal));
        binding.resultadoVisitante.setText(String.valueOf(puntajeVisitante));

        if (puntajeLocal > puntajeVisitante) {
            binding.resultado.setText("Ganó el " + Nlocal);
        } else if (puntajeLocal < puntajeVisitante) {
            binding.resultado.setText("Ganó el " + NVisitante);
        } else {
            binding.resultado.setText("Empate");
        }
    }

    private void volver() {
        binding.buttonVolver.setOnClickListener(v -> {
            // Si solo quieres volver a la anterior:
            // finish();

            // Si quieres ir siempre al MainActivity:
            Intent intent = new Intent(resultado.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void cambiarTextos() {
        binding.Local.setText(Nlocal);
        binding.Visitante.setText(NVisitante);
    }
}
