package com.example.anhmarvelapi.gestorllaves

import java.io.File
import java.io.FileInputStream
import java.util.Properties

/**
 * Clase que se enfoca en la recuperación segura de claves desde local.properties
 * Se puede instanciar esta clase sin parámetros para que mire en local.properties.
 * Se puede pasar como parámetro otro archivo si se estima conveniente leerlo.
 */
class LectorLlaves(private val ruta: String = "../local.properties") {
    private val propertiesFile = File(ruta).absoluteFile

    /**
     * Función que se encarga de tomar valores de local.properties o de otro archivo .properties
     * @param key la clave de la que queremos obtener el valor
     * @return el valor en formato String
     */
    fun leerPropiedad(key: String): String{
        val properties = Properties()
        val input = FileInputStream(propertiesFile)
        properties.load(input)
        return properties.getProperty(key)
    }

}