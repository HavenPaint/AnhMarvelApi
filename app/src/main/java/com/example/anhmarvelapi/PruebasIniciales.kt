package com.example.anhmarvelapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.anhmarvelapi.gestorllaves.ProcesadorLlaves
import com.example.anhmarvelapi.lecturaapi.AccionesAPI
import com.squareup.picasso.Picasso

class PruebasIniciales : AppCompatActivity() {

    lateinit var botonLeer: Button
    lateinit var imagen: ImageView
    lateinit var nombre: TextView
    lateinit var descripcion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Inicializando botones
        botonLeer = findViewById(R.id.botonLeer)
        imagen = findViewById(R.id.foto)
        nombre = findViewById(R.id.nombre)
        descripcion = findViewById(R.id.descripcion)

        //Interacción del botón
        botonLeer.setOnClickListener() {
            pruebaObtenerPersonaje()
            pruebaObtenerTodosPersonajes()
        }


    }

    private fun pruebaObtenerTodosPersonajes() {
        val pl = devolverProcesadorLlaves()

        AccionesAPI(pl).obtenerTodosPersonajesLambda {
            if (it != null) {
                Log.i("TODOSPERSONAJES", "Ha salido la lista sin null")

            }
        }
    }

    private fun pruebaObtenerPersonaje() {

        val pl = devolverProcesadorLlaves()

        AccionesAPI(pl).obtenerPersonajeLambda {
            if (it != null) {
                Log.i("PETICION_ACTIVITY", "$it ha sido pasado")
                nombre.text = it.name
                descripcion.text = it.description
                val url: String = "${it.thumbnail.path}.${it.thumbnail.extension}"
                Picasso.get().load(url).fit().centerCrop().into(imagen)

            }
        }
    }

    private fun devolverProcesadorLlaves(): ProcesadorLlaves {
        return ProcesadorLlaves(
            getString(R.string.MY_PUBLIC_KEY),
            getString(R.string.MY_PRIVATE_KEY)
        )
    }


}