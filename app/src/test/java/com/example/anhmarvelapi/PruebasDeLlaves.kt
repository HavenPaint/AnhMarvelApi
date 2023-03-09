package com.example.anhmarvelapi

import com.example.anhmarvelapi.gestorllaves.LectorLlaves
import com.example.anhmarvelapi.gestorllaves.ProcesadorLlaves
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PruebasDeLlaves {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun lecturaLlavesLocalProperties() {
        val claveEsperada="30a7b35c7383f41ab708d52df6b4dc21"
        val lectorLlaves: LectorLlaves = LectorLlaves()
        val claveLeida: String = lectorLlaves.leerPropiedad("MY_PUBLIC_KEY")
        assertEquals(claveEsperada, claveLeida)
    }
    @Test
    fun pruebaDeTimestamp(){
        val tsEsperado = (System.currentTimeMillis() / 1000L).toString()
        val tsProcesado = ProcesadorLlaves("aaaa","bbbb").generarTimestamp()
        assertEquals(tsEsperado, tsProcesado)
    }
    @Test
    fun pruebaDeParametrosAPI(){
        val apikey: String = LectorLlaves().leerPropiedad("MY_PUBLIC_KEY")
        val privateKey: String = LectorLlaves().leerPropiedad("MY_PRIVATE_KEY")
        val procesador: ProcesadorLlaves = ProcesadorLlaves(apikey,privateKey)
        val mapa: Map<String, String> = procesador.devolverParametrosNecesarios()
        val hash = procesador.generarHash(mapa["ts"]!!)
        assertEquals(mapa["hash"], hash)
        assertEquals(apikey, mapa["apikey"])
    }
}