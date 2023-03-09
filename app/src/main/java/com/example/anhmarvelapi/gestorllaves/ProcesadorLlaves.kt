package com.example.anhmarvelapi.gestorllaves

import java.math.BigInteger
import java.security.MessageDigest
import kotlin.collections.Map as Map

/**
 * Clase que se encarga de gestionar los parámetros necesarios para usar la API.
 * La API de Marvel necesita tres parámetros: ts, apikey y hash.
 * El parámetro ts es un timestamp de tipo unix: un número entero que representa el total de segundos desde 1970.
 * El parámetro apikey es la clave pública que se obtiene en la página de Marvel al registrarse.
 * El paŕametro hash es un hash propiamente dicho, creado a traves de la concatenación de tres strings:
 * ts, clave privada y clave pública.
 * El orden debe ser el que acabamos de mencionar o la API no funcionará.
 * Por supuesto el timestamp del parámetro y el utilizado para el hash también deben ser iguales.
 */
class ProcesadorLlaves(private val publicKey: String, private val privateKey: String) {
    var ultimoTimestamp: String = ""

    /**
     * Método que genera el hash necesario para usa como parámetro en la API de Marvel.
     * Para que el hash sea correcto, la secuencia debe ser la siguiente:
     * timestamp, clave privada, clave pública.
     * Cualquier otro orden alterará el hash y esto hará que no funcionen las consultas a la API.
     *
     * @param timestamp debe ser el valor en String de un timestamp tipo Unix
     */
    fun generarHash(timestamp: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val message = "$timestamp$privateKey$publicKey"
        val digest = md5.digest(message.toByteArray())
        return BigInteger(1, digest).toString(16).padStart(32, '0')
    }

    /**
     * Función que genera un timestamp tipo Unix
     */
    fun generarTimestamp(): String{
        val timestamp = System.currentTimeMillis() / 1000L
        return timestamp.toString()
    }

    /**
     * Función que devuelve un Map con los tres datos necesarios para hacer funcionar la API de Marvel
     */
    fun devolverParametrosNecesarios(): Map<String,String> {
        val ts = generarTimestamp()
        ultimoTimestamp = ts
        val apikey = publicKey
        val hash = generarHash(ts)
        val parametrosAPI = mapOf("ts" to ts, "apikey" to apikey, "hash" to hash)
        return parametrosAPI
    }


    /**
     * Función que devuelve un string con los parámetros que necesita la consulta a la API.
     * El String queda ya listo para ser añadido a la URL y que la API funcione.
     * OJO: Se formula sin usar "?" ni "&" al principio. Eso tendrá que ser insertado según sea oportuno en cada consulta.
     * @param map Un Mapa que contiene tres pares clave-valor con los parámetros que la API necesita para funcionar
     */
    fun devolverStringParametros(map: Map<String, String>): String{
        val sb = StringBuilder()
        sb.append("ts=")
            .append(map["ts"])
            .append("&apikey=")
            .append(map["apikey"])
            .append("&hash=")
            .append(map["hash"])
        return sb.toString()
    }

    /**
     * Función sin parámetros: en su lugar adquiere el objeto Map y llama a la función con parámetro
     * OJO: Se formula sin usar "?" ni "&" al principio. Eso tendrá que ser insertado según sea oportuno en cada consulta.
     */
    fun devolverStringParametros(): String {
        val map: Map<String, String> = devolverParametrosNecesarios()
        return devolverStringParametros(map)
    }

}