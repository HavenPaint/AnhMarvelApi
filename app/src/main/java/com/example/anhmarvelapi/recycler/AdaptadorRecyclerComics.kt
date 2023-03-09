package com.example.anhmarvelapi.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.anhmarvelapi.R
import com.example.anhmarvelapi.objetos.Comic
import com.example.anhmarvelapi.objetos.Personaje
import com.squareup.picasso.Picasso

class AdaptadorRecyclerComics(private val items: List<Comic>, private val listener: OnItemClickListener) : RecyclerView.Adapter<ItemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_layout, parent, false)
        return ItemViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        Picasso.get().load(item.thumbnail.devolverURLCompleta()).fit().centerCrop().into(holder.recyclerFoto)
        holder.recyclerNombre.text = item.name
        holder.recyclerDescripcion.text = item.description
    }
}