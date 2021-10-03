package app.antran.pexels.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.antran.pexels.Greeting
import app.antran.pexels.CounterViewModel
import app.antran.pexels.GitHubRepository
import app.antran.pexels.GitHubViewModel
import kotlinx.coroutines.launch

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                HomeScreen()
            }
        }
    }
}

@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {

    val count by viewModel.count.observeAsState(0)

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = greet())

        Row() {
            Button(onClick = { viewModel.increase() }) {
                Text("+")
            }
            Text(text = "Count: ${count}", modifier = Modifier.padding(8.dp))
            Button(onClick = { viewModel.decrease() }) {
                Text("-")
            }
        }

        Button(onClick = { viewModel.fetchRepos() }) {
            Text("Fetch GitHub Repository")
        }
    }
}

class HomeViewModel: ViewModel() {
    private var _count = MutableLiveData<Int>(0)
    var count: androidx.lifecycle.LiveData<Int> = _count

    private var _homeViewModel = CounterViewModel()

    private var _githubViewModel = GitHubViewModel()

    init {
        _homeViewModel.count.addObserver { value ->
            _count.value = value
        }
    }

    fun increase() {
        _homeViewModel.increase()
    }

    fun decrease() {
        _homeViewModel.decrease()
    }

    fun fetchRepos() {
        _githubViewModel.fetchRepos()
    }
}


@Composable
@Preview
fun PreviewHomeScreen() {
    MaterialTheme {
        HomeScreen()
    }
}