package com.example.guia5.DataBase;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.guia5.DAO.PersonaDAO;
import com.example.guia5.Entidades.Personas;

@Database(entities = {Personas.class}, version = 1)
//Version cambiara si cambia otra version
public abstract class PersonasDataBase extends RoomDatabase {
    private static PersonasDataBase instance;
    public abstract PersonaDAO personaDAO();
    public static synchronized PersonasDataBase getInstance(Context context){
        if (instance == null) {
        instance = Room.databaseBuilder(context.getApplicationContext(),
                PersonasDataBase.class, "personasdb")
                .fallbackToDestructiveMigration()
                .build();
        }
        return instance;
    }
}
