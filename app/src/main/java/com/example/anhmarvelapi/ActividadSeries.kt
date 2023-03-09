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
import com.example.anhmarvelapi.objetos.Serie
import com.example.anhmarvelapi.recycler.AdaptadorRecyclerComics
import com.example.anhmarvelapi.recycler.AdaptadorRecyclerSeries
import com.example.anhmarvelapi.recycler.ItemViewHolder
import com.example.anhmarvelapi.recycler.OnItemClickListener

class ActividadSeries : AppCompatActivity(), OnItemClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_series)

        //Inicializaciones
        pruebaObtenerTodosSeries { items ->

            Log.i("ITEMS", "$items")
            val recyclerView: RecyclerView = findViewById(R.id.seriesRecycler)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = AdaptadorRecyclerSeries(items, this)

        }
    }

    private fun devolverProcesadorLlaves(): ProcesadorLlaves {
        return ProcesadorLlaves(
            getString(R.string.MY_PUBLIC_KEY),
            getString(R.string.MY_PRIVATE_KEY)
        )
    }

    private fun pruebaObtenerTodosSeries(callback: (List<Serie>) -> Unit) {
        val pl = devolverProcesadorLlaves()

        AccionesAPI(pl).obtenerTodosSeriesLambda {
            if (it != null) {
                Log.i("TODOSSERIES", "Ha salido la lista sin null")
                callback(it)
            }else{
                callback(ArrayList<Serie>())
            }
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Click en objeto: $position. Sin implementar", Toast.LENGTH_SHORT).show()
    }


}