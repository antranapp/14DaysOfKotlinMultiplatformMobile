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
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.antran.pexels.Greeting

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
    }
}

class HomeViewModel: ViewModel() {
    private var _count = MutableLiveData<Int>(0)
    var count: LiveData<Int> = _count

    fun increase() {
        _count.value?.let { a ->
            _count.value = a + 1
        }
    }

    fun decrease() {
        _count.value?.let { a ->
            if (a > 0) {
                _count.value = a - 1
            }
        }
    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    MaterialTheme {
        HomeScreen()
    }
}