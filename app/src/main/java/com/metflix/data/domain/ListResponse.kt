package com.metflix.data.domain

data class ListResponse<T>(
    val page: Int,
    val results: List<T>,
    val total_results: Int,
    val total_pages: Int
)