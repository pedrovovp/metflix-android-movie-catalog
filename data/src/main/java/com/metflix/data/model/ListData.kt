package com.metflix.data.model

data class ListData<T>(
    val page: Int,
    val results: List<T>,
    val total_results: Int,
    val total_pages: Int
)