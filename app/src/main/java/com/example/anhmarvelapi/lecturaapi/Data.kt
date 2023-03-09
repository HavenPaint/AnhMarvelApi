package com.example.anhmarvelapi.lecturaapi

data class Data<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<T>
)
