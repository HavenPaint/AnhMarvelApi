package com.example.anhmarvelapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuPrincipal : AppCompatActivity() {

    lateinit var botonPersonajes: Button
    lateinit var botonComics: Button
    lateinit var botonSeries: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        //Inicializando
        botonPersonajes = findViewById(R.id.botonPersonajes)
        botonComics = findViewById(R.id.botonComics)
        botonSeries = findViewById(R.id.botonSeries)

        botonPersonajes.setOnClickListener() {
            val intent = Intent(this, ActividadPersonajes::class.java)
            startActivity(intent)
        }
        botonComics.setOnClickListener() {
            val intent = Intent(this, ActividadComics::class.java)
            startActivity(intent)
        }
        botonSeries.setOnClickListener() {
            val intent = Intent(this, ActividadSeries::class.java)
            startActivity(intent)
        }
    }
}