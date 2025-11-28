package com.example.basketballscoreapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Importante: este import lo genera ViewBinding
import com.example.basketballscoreapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // ViewBinding para acceder a las vistas sin usar findViewById

    // Variables para los marcadores
    private int local = 0;
    private int visitante = 0;

    // Variables para guardar los nombres de los equipos
    private String Nlocal;
    private String NVisitante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inicializamos el ViewBinding e inflamos el layout
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Ajustamos los márgenes para evitar que la UI choque con la barra de estado o navegación
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Configuramos toda la lógica de los botones
        ConfigurarBotones();

        // Mostramos los marcadores al iniciar la actividad
        ActualizarMarcadores();
    }

    private void ConfigurarBotones() {
        /*
            Cada botón modifica el marcador correspondiente.
            También se comprueba que los valores no sean negativos.
        */

        // Sumar 1 punto al equipo local
        binding.botonSumar1Local.setOnClickListener(v -> {
            local++;
            if (local >= 0) {
                ActualizarMarcadores();
            } else {
                local = 0;
            }
        });

        // Sumar 1 punto al visitante
        binding.botonSumar1Visitante.setOnClickListener(v -> {
            visitante++;
            if (visitante >= 0) {
                ActualizarMarcadores();
            } else {
                visitante = 0;
            }
        });

        // Sumar 2 puntos al local
        binding.botonSumar2Local.setOnClickListener(v -> {
            local += 2;
            if (local >= 0) {
                ActualizarMarcadores();
            } else {
                local = 0;
            }
        });

        // Sumar 2 puntos al visitante
        binding.botonSumar2Visitante.setOnClickListener(v -> {
            visitante += 2;
            if (visitante >= 0) {
                ActualizarMarcadores();
            } else {
                visitante = 0;
            }
        });

        // Restar 1 punto al local
        binding.botonRestarLocal4.setOnClickListener(v -> {
            local -= 1;
            if (local >= 0) {
                ActualizarMarcadores();
            } else {
                local = 0;
            }
        });

        // Restar 1 punto al visitante
        binding.botonRestarVisitante.setOnClickListener(v -> {
            visitante -= 1;
            if (visitante >= 0) {
                ActualizarMarcadores();
            } else {
                visitante = 0;
            }
        });

        // Botón para reiniciar completamente el marcador
        binding.buttonReiniciar.setOnClickListener(v -> {
            local = 0;
            visitante = 0;
            ActualizarMarcadores();
        });

        // Botón para pasar a la actividad de resultados
        binding.buttonPasarVentana.setOnClickListener(v -> {

            // Obtenemos los nombres de los equipos desde los EditText
            Nlocal = binding.Local.getText().toString();
            NVisitante = binding.visitante.getText().toString();

            // Animación al cambiar de ventana
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            // Creamos el intent para abrir la actividad "resultado"
            Intent intent = new Intent(this, resultado.class);

            // Enviamos los marcadores y los nombres de los equipos
            intent.putExtra("local", local);
            intent.putExtra("visitante", visitante);
            intent.putExtra("Nlocal", Nlocal);
            intent.putExtra("NVisitante", NVisitante);

            // Iniciamos la nueva actividad
            startActivity(intent);
        });
    }

    private void ActualizarMarcadores() {
        // Actualiza los textos en pantalla para mostrar el marcador actual
        binding.numeroLocal.setText(String.valueOf(local));
        binding.numeroLocal2.setText(String.valueOf(visitante));
    }
}
