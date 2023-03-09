package com.example.anhmarvelapi.lecturaapi

data class ApiResponse<T>(
    val code: Int,
    val status: String,
    val data: Data<T>
)
