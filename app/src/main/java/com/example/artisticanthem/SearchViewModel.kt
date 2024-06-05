package com.example.artisticanthem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artisticanthem.api.AuthorsResponse
import com.example.artisticanthem.api.PoemResponse
import com.example.artisticanthem.api.RetrofitClient
import kotlinx.coroutines.launch
import android.util.Log
import com.example.artisticanthem.api.Wrapper

class SearchViewModel : ViewModel() {
    var poems: List<PoemResponse> = emptyList()
        private set

    var errorMessage: String? = null
        private set

    fun searchPoem(inputField: String, searchTerm: String, searchType: String, outputField: String, format: String) {
        viewModelScope.launch {
            try {
                Log.d("SearchViewModel", "Searching for poem with inputField: $inputField, searchTerm: $searchTerm, searchType: $searchType, outputField: $outputField, format: $format")
                val wrapper = RetrofitClient.poetryApiService.searchPoem(inputField, searchTerm, searchType, outputField, format) as Wrapper
                val authorsResponse = wrapper.response
                poems = authorsResponse.authors.map { author -> PoemResponse(author, "", listOf(), "") }
                Log.d("SearchViewModel", "Received poems: $poems")
                errorMessage = null
            } catch (e: Exception) {
                Log.e("SearchViewModel", "Error searching for poem", e)
                errorMessage = e.message
            }
        }
    }
}