import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.artisticanthem.SearchViewModel

@Composable
fun SearchScreen(navController: NavHostController) {
    val viewModel: SearchViewModel = viewModel()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    var searchType by remember { mutableStateOf(SearchType.AUTHOR) }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            RadioButton(
                selected = searchType == SearchType.AUTHOR,
                onClick = { searchType = SearchType.AUTHOR }
            )
            Text(text = "Author", modifier = Modifier.padding(start = 4.dp))

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = searchType == SearchType.TITLE,
                onClick = { searchType = SearchType.TITLE }
            )
            Text(text = "Title", modifier = Modifier.padding(start = 4.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                when (searchType) {
                    SearchType.AUTHOR -> viewModel.searchPoem("author", searchQuery.text, "", "author,title", "json")
                    SearchType.TITLE -> viewModel.searchPoem("title", searchQuery.text, "", "author,title", "json")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.errorMessage != null) {
            Text(text = "Error: ${viewModel.errorMessage}", color = MaterialTheme.colorScheme.error)
        }

        LazyColumn {
            items(viewModel.poems) { poem ->
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("details/${poem.title}/${poem.author}")
                        }
                ) {
                    Text(text = poem.title, style = MaterialTheme.typography.headlineSmall)
                    Text(text = poem.author, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

    }

}
enum class SearchType {
    AUTHOR, TITLE
}