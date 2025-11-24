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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Usamos ViewBinding
        binding = ActivityResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ajustar insets con binding.getRoot()
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });
        ActualizarResultado();
        volver();
    }

    private void ActualizarResultado() {
        int puntajeLocal = getIntent().getIntExtra("local", 0);
        int puntajeVisitante = getIntent().getIntExtra("visitante", 0);

        binding.resultadoLocal.setText(String.valueOf(puntajeLocal));
        binding.resultadoVisitante.setText(String.valueOf(puntajeVisitante));


        if (puntajeLocal > puntajeVisitante) {
            binding.resultado.setText("Ganó el equipo local");
        } else if (puntajeLocal < puntajeVisitante) {
            binding.resultado.setText("Ganó el equipo visitante");
        } else {
            binding.resultado.setText("Empate");
        }
    }

    private void volver() {
        binding.buttonVolver.setOnClickListener(v -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(getIntent());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });


    }

}
