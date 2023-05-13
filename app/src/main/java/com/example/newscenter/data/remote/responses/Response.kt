package com.example.newscenter.data.remote.responses

data class Response(
    val category: String,
    val description: String,
    val id: String,
    val image: String,
    val title: String,
    val url: String
)