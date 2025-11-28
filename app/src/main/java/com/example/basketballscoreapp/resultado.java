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

    private ActivityResultadoBinding binding; // ViewBinding para acceder a las vistas fácilmente
    private String Nlocal;       // Nombre del equipo local
    private String NVisitante;   // Nombre del equipo visitante

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inicializamos el ViewBinding e inflamos el layout
        binding = ActivityResultadoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ajustamos márgenes para evitar que la UI choque con barras del sistema
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtenemos del intent los nombres de los equipos enviados desde la actividad anterior
        Intent intent = getIntent();
        Nlocal = intent.getStringExtra("Nlocal");
        NVisitante = intent.getStringExtra("NVisitante");

        // Mostramos resultados y texto ganador/perdedor
        ActualizarResultado();

        // Rellenamos los TextView con los nombres de los equipos
        cambiarTextos();

        // Configuramos el botón para volver atrás
        volver();
    }

    private void ActualizarResultado() {
        // Recuperamos las puntuaciones enviadas desde MainActivity
        Intent intent = getIntent();
        int puntajeLocal = intent.getIntExtra("local", 0);
        int puntajeVisitante = intent.getIntExtra("visitante", 0);

        // Mostramos las puntuaciones en pantalla
        binding.resultadoLocal.setText(String.valueOf(puntajeLocal));
        binding.resultadoVisitante.setText(String.valueOf(puntajeVisitante));

        // Comprobamos quién ganó y mostramos el mensaje correspondiente
        if (puntajeLocal > puntajeVisitante) {
            binding.resultado.setText("Ganó el " + Nlocal);
        } else if (puntajeLocal < puntajeVisitante) {
            binding.resultado.setText("Ganó el " + NVisitante);
        } else {
            binding.resultado.setText("Empate");
        }
    }

    private void volver() {
        // Listener del botón para volver a la pantalla principal
        binding.buttonVolver.setOnClickListener(v -> {

            // Si quieres que siempre vaya al MainActivity:
            Intent intent = new Intent(resultado.this, MainActivity.class);
            startActivity(intent);

            // Cerramos esta actividad para que no quede en la pila
            finish();
        });
    }

    private void cambiarTextos() {
        // Ponemos los nombres de cada equipo en sus correspondientes TextView
        binding.Local.setText(Nlocal);
        binding.Visitante.setText(NVisitante);
    }
}
