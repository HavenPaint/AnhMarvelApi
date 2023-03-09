package com.example.anhmarvelapi.objetos

import com.google.gson.annotations.SerializedName

/**
 * A pesar de que el nombre serializado sea título, que es el que se recupera de la API,
 * la estructura de esta clase es la misma que la de personajes.
 */
data class Comic(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
){
    constructor() : this(0, "", "", Thumbnail())
}