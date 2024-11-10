package com.example.guia5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.guia5.Entidades.Personas;

public class EditarPersonaActivity extends AppCompatActivity {

    private EditText etNombre, etApellido, etEdad;
    private Button btnGuardar;  // Declaración del botón
    private Personas persona;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_persona);

        // Configuración de insets para ajustes de pantalla completa
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar los EditTexts
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);

        // Inicializar el botón
        btnGuardar = findViewById(R.id.btnGuardar);

        // Recibir el objeto y la posición desde el intent
        persona = (Personas) getIntent().getSerializableExtra("persona");  // Recibe el objeto Persona
        position = getIntent().getIntExtra("position", -1);  // Recibe la posición

        // Rellenar los campos si la persona es válida
        if (persona != null) {
            etNombre.setText(persona.nombrePersona);
            etApellido.setText(persona.apellidoPersona);
            etEdad.setText(String.valueOf(persona.edadPersona));
        }

        // Configurar el OnClickListener para el botón guardar
        btnGuardar.setOnClickListener(v -> {
            // Llamar al método que guarda los cambios
            guardarCambios();
        });
    }

    // Método para guardar cambios (ejemplo básico)
    private void guardarCambios() {
        // Actualizar los datos de la persona
        persona.nombrePersona = etNombre.getText().toString();
        persona.apellidoPersona = etApellido.getText().toString();
        persona.edadPersona = Integer.parseInt(etEdad.getText().toString());

        // Enviar el objeto actualizado de vuelta
        Intent resultIntent = new Intent();
        resultIntent.putExtra("personaActualizada", persona);  // Pasar el objeto Persona sin cast
        resultIntent.putExtra("position", position);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
