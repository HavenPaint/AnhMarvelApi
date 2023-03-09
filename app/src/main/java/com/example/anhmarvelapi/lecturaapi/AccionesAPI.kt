package com.example.anhmarvelapi.lecturaapi

import android.util.Log
import com.example.anhmarvelapi.gestorllaves.ProcesadorLlaves
import com.example.anhmarvelapi.objetos.Comic
import com.example.anhmarvelapi.objetos.Personaje
import com.example.anhmarvelapi.objetos.Serie
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class AccionesAPI(p: ProcesadorLlaves) {

    val pl = p
    val retrofit = Retrofit.Builder().baseUrl("https://gateway.marvel.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()
    val servicio = retrofit.create(MarvelAPI::class.java)

    init {
    }

    /**
     * Función que trata de coger nombre, descripción e id de un personaje concreto
     * Utilizado para pruebas
     * Utilizacion de Enqueue. En este estado hace que la UI siga ejecutando código antes de que
     * la consulta termine y devuelva los datos necesarios, causando un bug cuando la UI trata de mostrar datos
     */
    @Deprecated("Usar obtenerPersonajeLambda")
    fun obtenerPersonajeEnqueue(): Personaje {
        var pe = Personaje()

        //Tomando los parámetros que la API de marvel necesita
        val map = this.pl.devolverParametrosNecesarios()
        val ts = map["ts"]!!
        val apikey = map["apikey"]!!
        val hash = map["hash"]!!
        Log.i("MAP", "$ts - $apikey - $hash")

        //Llamada a la API
        val llamada = servicio.obtenerPersonaje(
            ts, apikey, hash
        )

        llamada.enqueue(object : Callback<ApiResponse<Personaje>> {
            override fun onResponse(
                call: Call<ApiResponse<Personaje>>, response: Response<ApiResponse<Personaje>>
            ) {
                if (response.isSuccessful) {
                    val personaje = response.body()?.data?.results?.get(0)

                    Log.i("API", "Character ID: ${personaje?.id}")
                    Log.i("API", "Character Name: ${personaje?.name}")
                    Log.i("API", "Character Description: ${personaje?.description}")
                    if (personaje != null) {
                        pe = personaje
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<Personaje>>, t: Throwable) {
                Log.e("API", "API request failed: ${t.message}")
            }
        })
        return pe
    }

    /**
     * Función que trata de coger nombre, descripción e id de un personaje concreto
     * Utilizado para pruebas
     * Versión Lambda y callback. Hacerlo de esta manera resuelve el problema de que
     * la función no espere a obtener los datos, causando que la UI siga ejecutando código antes de tiempo
     * sin tener los datos necesarios.
     */
    @Deprecated("Esta función se usó al principo para pruebas. Ya no se está usando")
    fun obtenerPersonajeLambda(callback: (Personaje?) -> Unit) {

        //Tomando los parámetros que la API de marvel necesita
        val map = this.pl.devolverParametrosNecesarios()
        val ts = map["ts"]!!
        val apikey = map["apikey"]!!
        val hash = map["hash"]!!
        Log.i("MAP", "$ts - $apikey - $hash")

        //Llamada a la API
        val llamada = servicio.obtenerPersonaje(
            ts, apikey, hash
        )

        llamada.enqueue(object : Callback<ApiResponse<Personaje>> {
            override fun onResponse(
                call: Call<ApiResponse<Personaje>>, response: Response<ApiResponse<Personaje>>
            ) {
                if (response.isSuccessful) {
                    val personaje = response.body()?.data?.results?.get(0)

                    Log.i("API", "Character ID: ${personaje?.id}")
                    Log.i("API", "Character Name: ${personaje?.name}")
                    Log.i("API", "Character Description: ${personaje?.description}")
                    Log.i("API", "Character thumbnail: ${personaje?.thumbnail}")
                    if (personaje != null) {
                        callback(personaje)
                    }
                }else{
                    Log.e("API", "Fallo de respuesta: ${response.message()}")
                    callback(Personaje())
                }
            }

            override fun onFailure(call: Call<ApiResponse<Personaje>>, t: Throwable) {
                Log.e("API", "Fallo de petición: ${t.message}")
                callback(Personaje())
            }
        })
    }

    /**
     * Función que trata de coger nombre, descripción e id de todos los personajes
     * Utilizado para pruebas.
     * No usa lambda y callback, así que tendrá problemas
     * @
     */
    fun obtenerTodosPersonajesLambda(callback: (List<Personaje>?) -> Unit)  {

        //Tomando los parámetros que la API de marvel necesita
        val map = this.pl.devolverParametrosNecesarios()
        val ts = map["ts"]!!
        val apikey = map["apikey"]!!
        val hash = map["hash"]!!
        Log.i("MAP", "$ts - $apikey - $hash")


        //Llamada a la API
        val llamada = servicio.obtenerTodosPersonajes(
            ts, apikey, hash
        )

        llamada.enqueue(object : Callback<ApiResponse<Personaje>> {
            override fun onResponse(
                call: Call<ApiResponse<Personaje>>, response: Response<ApiResponse<Personaje>>
            ) {
                if (response.isSuccessful) {
                    val resultados: List<Personaje>? = response.body()?.data?.results
                    if (resultados != null) {
                        for (personaje in resultados) {
                            Log.d("API", "Character ID: ${personaje?.id}")
                            Log.d("API", "Character Name: ${personaje?.name}")
                            Log.d("API", "Character Description: ${personaje?.description}")

                        }
                        callback(resultados)
                    }else{
                        Log.e("API", "Fallo de respuesta: ${response.message()}")
                        callback(ArrayList<Personaje>())
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<Personaje>>, t: Throwable) {
                Log.e("API", "Fallo de petición: ${t.message}")
                callback(ArrayList<Personaje>())
            }
        })
    }

    /**
     * Función que trata de coger nombre, descripción e id de todos los cómics
     */
    fun obtenerTodosComicsLambda(callback: (List<Comic>?) -> Unit)  {

        //Tomando los parámetros que la API de marvel necesita
        val map = this.pl.devolverParametrosNecesarios()
        val ts = map["ts"]!!
        val apikey = map["apikey"]!!
        val hash = map["hash"]!!
        Log.i("MAP", "$ts - $apikey - $hash")


        //Llamada a la API
        val llamada = servicio.obtenerTodosComics(
            ts, apikey, hash
        )

        llamada.enqueue(object : Callback<ApiResponse<Comic>> {
            override fun onResponse(
                call: Call<ApiResponse<Comic>>, response: Response<ApiResponse<Comic>>
            ) {
                if (response.isSuccessful) {
                    val resultados: List<Comic>? = response.body()?.data?.results
                    if (resultados != null) {
                        for (comic in resultados) {
                            Log.d("API", "Character ID: ${comic?.id}")
                            Log.d("API", "Character Name: ${comic?.name}")
                            Log.d("API", "Character Description: ${comic?.description}")

                        }
                        callback(resultados)
                    }else{
                        Log.e("API", "Fallo de respuesta: ${response.message()}")
                        callback(ArrayList<Comic>())
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<Comic>>, t: Throwable) {
                Log.e("API", "Fallo de petición: ${t.message}")
                callback(ArrayList<Comic>())
            }
        })
    }

    /**
     * Función que trata de coger nombre, descripción e id de todos los cómics
     */
    fun obtenerTodosSeriesLambda(callback: (List<Serie>?) -> Unit)  {

        //Tomando los parámetros que la API de marvel necesita
        val map = this.pl.devolverParametrosNecesarios()
        val ts = map["ts"]!!
        val apikey = map["apikey"]!!
        val hash = map["hash"]!!
        Log.i("MAP", "$ts - $apikey - $hash")


        //Llamada a la API
        val llamada = servicio.obtenerTodosSeries(
            ts, apikey, hash
        )

        llamada.enqueue(object : Callback<ApiResponse<Serie>> {
            override fun onResponse(
                call: Call<ApiResponse<Serie>>, response: Response<ApiResponse<Serie>>
            ) {
                if (response.isSuccessful) {
                    val resultados: List<Serie>? = response.body()?.data?.results
                    if (resultados != null) {
                        for (serie in resultados) {
                            Log.d("API", "Character ID: ${serie?.id}")
                            Log.d("API", "Character Name: ${serie?.name}")
                            Log.d("API", "Character Description: ${serie?.description}")

                        }
                        callback(resultados)
                    }else{
                        Log.e("API", "Fallo de respuesta: ${response.message()}")
                        callback(ArrayList<Serie>())
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<Serie>>, t: Throwable) {
                Log.e("API", "Fallo de petición: ${t.message}")
                callback(ArrayList<Serie>())
            }
        })
    }



    /**
     * Función que trata de coger nombre, descripción e id de todos los personajes
     * Utilizado para pruebas.
     * No usa lambda y callback, así que tendrá problemas
     *
     */
    @Deprecated("Usar obtenerTodosPersonajesLambda()")
    fun obtenerTodosPersonajesEnqueue() {

        //Tomando los parámetros que la API de marvel necesita
        val map = this.pl.devolverParametrosNecesarios()
        val ts = map["ts"]!!
        val apikey = map["apikey"]!!
        val hash = map["hash"]!!
        Log.i("MAP", "$ts - $apikey - $hash")


        //Llamada a la API
        val llamada = servicio.obtenerTodosPersonajes(
            ts, apikey, hash
        )

        llamada.enqueue(object : Callback<ApiResponse<Personaje>> {
            override fun onResponse(
                call: Call<ApiResponse<Personaje>>, response: Response<ApiResponse<Personaje>>
            ) {
                if (response.isSuccessful) {
                    val resultados: List<Personaje>? = response.body()?.data?.results
                    if (resultados != null) {
                        for (personaje in resultados) {
                            Log.i("API", "Character ID: ${personaje?.id}")
                            Log.i("API", "Character Name: ${personaje?.name}")
                            Log.i("API", "Character Description: ${personaje?.description}")

                        }
                    }
                }
            }

            override fun onFailure(call: Call<ApiResponse<Personaje>>, t: Throwable) {
                Log.e("API", "API request failed: ${t.message}")
            }
        })
    }




}

