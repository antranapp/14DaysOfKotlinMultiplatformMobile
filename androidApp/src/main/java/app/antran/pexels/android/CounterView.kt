package app.antran.pexels.android

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.antran.pexels.CounterViewModel

@Composable
fun CounterView(viewModel: app.antran.pexels.android.CounterViewModel = app.antran.pexels.android.CounterViewModel()) {

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

class CounterViewModel: ViewModel() {
    private var _count = MutableLiveData<Int>(0)
    var count: androidx.lifecycle.LiveData<Int> = _count

    private var _counterViewModel = CounterViewModel()

    init {
        _counterViewModel.count.addObserver { value ->
            _count.value = value
        }
    }

    fun increase() {
        _counterViewModel.increase()
    }

    fun decrease() {
        _counterViewModel.decrease()
    }
}


@Composable
@Preview
fun PreviewCounterView() {
    MaterialTheme {
        CounterView()
    }
}