package com.example.newscenter.data.remote.responses

data class Article(
    val nextPage: Any,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)