package com.example.artisticanthem

import androidx.lifecycle.ViewModel
import com.example.artisticanthem.api.PoemResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel : ViewModel() {
    private val _favoritePoems = MutableStateFlow<List<PoemResponse>>(emptyList())
    val favoritePoems: StateFlow<List<PoemResponse>> = _favoritePoems

    fun addFavorite(poem: PoemResponse) {
        _favoritePoems.value = _favoritePoems.value + poem
    }

    fun removeFavorite(poem: PoemResponse) {
        _favoritePoems.value = _favoritePoems.value - poem
    }
}