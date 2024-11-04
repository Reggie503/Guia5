package com.example.guia5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guia5.Entidades.Personas;
import com.example.guia5.Model.PersonaViewModel;

import java.util.List;



public class MostrarListaActivity extends AppCompatActivity {

    private PersonaViewModel personaViewModel;
    private PersonaAdapter personaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_lista);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personaAdapter = new PersonaAdapter();
        recyclerView.setAdapter(personaAdapter);
        personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);
        personaViewModel.getListaDePersonas().observe(this, personas -> {
            personaAdapter.setPersonasList(personas);
        });
//setOnItemClickListener update
//setOnItemLongClickListener eliminar

    }
}
