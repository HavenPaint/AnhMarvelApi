package com.example.anhmarvelapi.objetos

import com.google.gson.annotations.SerializedName

data class Personaje(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("thumbnail") val thumbnail: Thumbnail
) {
    constructor() : this(0, "", "", Thumbnail())
}
