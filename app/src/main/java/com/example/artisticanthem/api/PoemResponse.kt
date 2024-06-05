package com.example.artisticanthem.api

data class PoemResponse(
    val title: String,
    val author: String,
    val lines: List<String>,
    val linecount: String
)


