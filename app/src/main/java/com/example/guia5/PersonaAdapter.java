package com.example.guia5;

import static androidx.core.app.ActivityCompat.startActivityForResult;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guia5.Entidades.Personas;
import com.example.guia5.R;

import java.util.ArrayList;
import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {

    private List<Personas> listaPersonas;
    private Context context;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public PersonaAdapter(Context context) {
        this.context = context;
        this.listaPersonas = new ArrayList<>();
    }

    // Interfaces para clics cortos y largos

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    // Métodos para establecer los listeners
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    public void setListaPersonas(List<Personas> lista) {
        this.listaPersonas = lista;
        notifyDataSetChanged();
    }

    public Personas getPersonaAt(int position) {
        return listaPersonas.get(position);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Personas persona = listaPersonas.get(position);
        holder.tvNombre.setText("Nombre: " + persona.nombrePersona);
        holder.tvApellido.setText("Apellido: " + persona.apellidoPersona);
        holder.tvEdad.setText("Edad: " + persona.edadPersona);

        // Configurando el clic corto
        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(holder.getAdapterPosition());
            }
        });

        // Configurando el clic largo
        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) {
                longClickListener.onItemLongClick(holder.getAdapterPosition());
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return listaPersonas != null ? listaPersonas.size() : 0;
    }

    public void updatePersona(int position, Personas personaActualizada) {
        listaPersonas.set(position, personaActualizada);
        notifyItemChanged(position);
    }


    static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvApellido;
        TextView tvEdad;

        PersonaViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellido = itemView.findViewById(R.id.tvApellido);
            tvEdad = itemView.findViewById(R.id.tvEdad);
        }
    }


}
