package com.example.guia5.Model;
import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.guia5.DAO.PersonaDAO;
import com.example.guia5.DataBase.PersonasDataBase;
import com.example.guia5.Entidades.Personas;

import java.util.List;

public class PersonaViewModel extends AndroidViewModel {
    private PersonaDAO personaDAO;
    private LiveData<List<Personas>> listaDePersonas;

    public PersonaViewModel(Application application) {
        super(application);

        PersonasDataBase dataBase = PersonasDataBase.getInstance(application);
        personaDAO = dataBase.personaDAO();
        listaDePersonas = personaDAO.obtenerTodasLasePersonas();
    }

    // Método para obtener la lista observable de todas las personas
    public LiveData<List<Personas>> getListaDePersonas() {
        return listaDePersonas;
    }

    // Método para insertar una persona en la base de datos
    public void insertarPersona(Personas persona) {
        new Thread(() -> personaDAO.Insertar(persona)).start();
    }

    // Método para eliminar una persona de la base de datos
    public void eliminarPersona(Personas persona) {
        new Thread(() -> personaDAO.Eliminar(persona)).start();
    }

    // Método para actualizar una persona en la base de datos
    public void actualizarPersona(Personas persona, int position) {
        new Thread(() -> personaDAO.Actualizar(persona)).start();
    }
}
