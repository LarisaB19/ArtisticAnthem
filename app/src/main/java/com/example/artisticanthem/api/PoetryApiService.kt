package com.example.artisticanthem.api

import retrofit2.http.GET
import retrofit2.http.Query

interface PoetryApiService {
    //https://poetrydb.org/author,poemcount/Dickinson;5
    @GET("api/poems")
    suspend fun getPoemsByAuthor(
        @Query("input_field") inputField: String = "author",
        @Query("search_term") searchTerm: String,
        @Query("search_type") searchType: String = "",
        @Query("output_field") outputField: String = "author,title",
        @Query("format") format: String = "json"
    ): List<PoemResponse>

    @GET("api/poems")
    suspend fun getPoemsByTitle(
        @Query("input_field") inputField: String = "title",
        @Query("search_term") searchTerm: String,
        @Query("search_type") searchType: String = "",
        @Query("output_field") outputField: String = "author,title",
        @Query("format") format: String = "json"
    ): List<PoemResponse>

    @GET("api/poems")
    suspend fun searchPoem(
        @Query("input_field") inputField: String,
        @Query("search_term") searchTerm: String,
        @Query("search_type") searchType: String,
        @Query("output_field") outputField: String,
        @Query("format") format: String
    ): List<PoemResponse>
}
