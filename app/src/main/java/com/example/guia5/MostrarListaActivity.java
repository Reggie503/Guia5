package com.example.guia5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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
    private Personas persona;
    private static final int REQUEST_CODE_EDIT = 1;
    private PersonaViewModel personaViewModel;
    private PersonaAdapter personaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_lista);

        // Inicializar RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicializar el adaptador
        personaAdapter = new PersonaAdapter(this);
        recyclerView.setAdapter(personaAdapter);

        // Obtener el ViewModel
        personaViewModel = new ViewModelProvider(this).get(PersonaViewModel.class);
        personaViewModel.getListaDePersonas().observe(this, personas -> {
            // Actualizar la lista del adaptador
            personaAdapter.setListaPersonas(personas);
        });

        // Manejar clic en un item para editar
        personaAdapter.setOnItemClickListener(position -> {
            Personas persona = personaAdapter.getPersonaAt(position);  // Obtener persona por la posición
            Intent intent = new Intent(MostrarListaActivity.this, EditarPersonaActivity.class);
            intent.putExtra("persona", persona);  // Pasar el objeto persona
            intent.putExtra("position", position);  // Pasar la posición
            startActivityForResult(intent, REQUEST_CODE_EDIT);  // Iniciar la actividad de edición
        });

        // Manejar clic largo para eliminar
        personaAdapter.setOnItemLongClickListener(position -> {
            new AlertDialog.Builder(this)
                    .setTitle("Eliminar Persona")
                    .setMessage("¿Estás seguro de que deseas eliminar esta persona?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        personaViewModel.eliminarPersona(personaAdapter.getPersonaAt(position));  // Eliminar persona
                    })
                    .setNegativeButton("No", null)  // No hacer nada si se cancela
                    .show();
        });
    }

    // Manejar el resultado de la edición de la persona (cuando se regresa de EditarPersonaActivity)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK) {
            if (data != null) {
                // Recuperar la persona actualizada
                Personas personaActualizada = (Personas) data.getSerializableExtra("personaActualizada");
                int position = data.getIntExtra("position", -1);

                if (personaActualizada != null && position != -1) {
                    // Actualizar la persona en la lista del ViewModel
                    personaViewModel.actualizarPersona(personaActualizada, position);
                }
            }
        }
    }





//setOnItemClickListener update
//setOnItemLongClickListener eliminar


}
