package com.example.anhmarvelapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anhmarvelapi.gestorllaves.ProcesadorLlaves
import com.example.anhmarvelapi.lecturaapi.AccionesAPI
import com.example.anhmarvelapi.objetos.Comic
import com.example.anhmarvelapi.recycler.AdaptadorRecyclerComics
import com.example.anhmarvelapi.recycler.AdaptadorRecyclerPersonajes
import com.example.anhmarvelapi.recycler.ItemViewHolder
import com.example.anhmarvelapi.recycler.OnItemClickListener

class ActividadComics : AppCompatActivity(), OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_comics)

        //Inicializaciones
        pruebaObtenerTodosComics { items ->

            Log.i("ITEMS", "$items")
            val recyclerView: RecyclerView = findViewById(R.id.comicsRecycler)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = AdaptadorRecyclerComics(items, this)

        }
    }

    private fun devolverProcesadorLlaves(): ProcesadorLlaves {
        return ProcesadorLlaves(
            getString(R.string.MY_PUBLIC_KEY),
            getString(R.string.MY_PRIVATE_KEY)
        )
    }

    private fun pruebaObtenerTodosComics(callback: (List<Comic>) -> Unit) {
        val pl = devolverProcesadorLlaves()

        AccionesAPI(pl).obtenerTodosComicsLambda {
            if (it != null) {
                Log.i("TODOSCOMICS", "Ha salido la lista sin null")
                callback(it)
            }else{
                callback(ArrayList<Comic>())
            }
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Click en objeto: $position. Sin implementar", Toast.LENGTH_SHORT).show()
    }


}