package com.mohitum.androidmvpkotlindemo.model

/**
 * Main movies API response data class
 */
data class MovieData(
        val Search: List<Search>,
        val totalResults: String,
        val Response: String
)