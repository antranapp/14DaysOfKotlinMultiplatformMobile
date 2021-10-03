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
import app.antran.pexels.GitHubRepo
import app.antran.pexels.GitHubViewModel

@Composable
fun GitHubRepositoryView(viewModel: GitHubRepositoryViewModel = GitHubRepositoryViewModel()) {

    val count by viewModel.repos.observeAsState(listOf())

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { viewModel.fetchRepos() }) {
            Text("Fetch GitHub Repository")
        }
    }
}

class GitHubRepositoryViewModel: ViewModel() {
    private var _repos = MutableLiveData<List<GitHubRepo>>(listOf())
    var repos: androidx.lifecycle.LiveData<List<GitHubRepo>> = _repos

    private var _githubViewModel = GitHubViewModel()

    init {
        _githubViewModel.repos.addObserver { value ->
            _repos.value = value
        }
    }

    fun fetchRepos() {
        _githubViewModel.fetchRepos()
    }
}


@Composable
@Preview
fun PreviewGitHubRepositoryView() {
    MaterialTheme {
        GitHubRepositoryView()
    }
}