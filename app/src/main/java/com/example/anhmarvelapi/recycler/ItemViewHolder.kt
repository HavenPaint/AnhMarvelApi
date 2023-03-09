package com.example.anhmarvelapi.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.anhmarvelapi.R

/**
 * Clase para el ViewHolder del RecyclerView, referenciando los elementos del layout diseñado al efecto.
 */
class ItemViewHolder(itemView: View, private val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val recyclerFoto: ImageView = itemView.findViewById(R.id.recyclerFoto)
    val recyclerNombre: TextView = itemView.findViewById(R.id.recyclerNombre)
    val recyclerDescripcion: TextView = itemView.findViewById(R.id.recyclerDescripcion)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        listener.onItemClick(adapterPosition)
    }



}