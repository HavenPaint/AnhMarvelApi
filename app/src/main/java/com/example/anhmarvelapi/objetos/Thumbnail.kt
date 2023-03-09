package com.example.anhmarvelapi.objetos

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path")
    var path: String? = "null",
    @SerializedName("extension")
    var extension: String? =" null"
)
{
    constructor() : this("","",)

    /**
     * Función que devuelve la URL completa incluyendo la extensión indicada por la API
     */
    fun devolverURLCompleta(): String{
        return "$path.$extension"
    }
}
