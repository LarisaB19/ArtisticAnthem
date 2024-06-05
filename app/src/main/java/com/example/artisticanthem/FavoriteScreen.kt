package com.example.artisticanthem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FavoriteScreen() {
    val viewModel: FavoriteViewModel = viewModel()
    val favoritePoems by viewModel.favoritePoems.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Favorite Poems", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(favoritePoems) { poem ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(poem.title, style = MaterialTheme.typography.headlineSmall)
                    Text(poem.author, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}