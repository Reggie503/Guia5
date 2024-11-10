package com.example.guia5.DAO;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.guia5.Entidades.Personas;

import java.util.List;

@Dao
public interface PersonaDAO {

    @Insert
    void Insertar(Personas persona);

    @Delete
    void Eliminar(Personas persona);

    @Update
    void Actualizar(Personas persona);

    @Query("SELECT * FROM Personas")
    LiveData<List<Personas>> obtenerTodasLasePersonas();
}

