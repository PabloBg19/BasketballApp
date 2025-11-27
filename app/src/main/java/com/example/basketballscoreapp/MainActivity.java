package com.example.basketballscoreapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// IMPORTANTE: este import lo genera ViewBinding
import com.example.basketballscoreapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding; // <- declaramos el binding
    private int local = 0; // <- declaramos las variables
    private int visitante = 0;

    private String Nlocal;

    private String NVisitante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Inflamos el layout con ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Usamos el root del binding en lugar de findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        ConfigurarBotones(); //llamamos a configurar botones

        ActualizarMarcadores(); //llamamos a actualizar marcadores
    }
    private void ConfigurarBotones(){
        /*
            toda esta clase funciona que si se pulsa el boton se suma uno al marcador
            tambien comprueba si es negativo y si lo es lo pone a 0 para que no haya resultados negativos
         */
        binding.botonSumar1Local.setOnClickListener(v -> {
            local++;
            if (local >=0){
                ActualizarMarcadores();
            }else{
                local = 0;

            }

        });

        binding.botonSumar1Visitante.setOnClickListener(v -> {
            visitante++;
            if (visitante >=0){
                ActualizarMarcadores();
            }else{
                visitante = 0;
            }



        });

        binding.botonSumar2Local.setOnClickListener(v -> {
            local += 2;
            if (local >=0){
                ActualizarMarcadores();
            }else{
                local = 0;
            }

        });

        binding.botonSumar2Visitante.setOnClickListener(v -> {
            visitante += 2;
            if (visitante >=0){
                ActualizarMarcadores();
            }else{
                visitante = 0;

            }

        });

        binding.botonRestarLocal4.setOnClickListener(v -> {
            local -= 1;
            if (local >=0){
                ActualizarMarcadores();
            }else{
                local = 0;
            }

        });

        binding.botonRestarVisitante.setOnClickListener(v -> {
            visitante -= 1;
            if (visitante >=0){
                ActualizarMarcadores();
            }else{
                visitante = 0;
            }

        });

        binding.buttonReiniciar.setOnClickListener(v -> {
            local = 0;
            visitante = 0;
            ActualizarMarcadores();
        });

        binding.buttonPasarVentana.setOnClickListener(v -> {
            binding.Local.getText().toString();
            binding.visitante.getText().toString();
            Nlocal = binding.Local.getText().toString();
            NVisitante = binding.visitante.getText().toString();

            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            Intent intent = new Intent(this, resultado.class);  // creamos el intent y pasamos a la otra actividad
            intent.putExtra("local", local); // pasamos los datos
            intent.putExtra("visitante", visitante);
            intent.putExtra("Nlocal", Nlocal);
            intent.putExtra("NVisitante", NVisitante);

            startActivity(intent);
        });

    }

    private void ActualizarMarcadores(){
        /*
            esta funcion actualiza los marcadores en el layout
         */
        binding.numeroLocal.setText(String.valueOf(local));
        binding.numeroLocal2.setText(String.valueOf(visitante));
    }
}
