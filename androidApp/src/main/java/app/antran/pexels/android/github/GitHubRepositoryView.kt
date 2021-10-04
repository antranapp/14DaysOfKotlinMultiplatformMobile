package app.antran.pexels.android.github

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.antran.pexels.CounterViewModel
import app.antran.pexels.GitHubRepo
import app.antran.pexels.GitHubViewModel
import com.skydoves.landscapist.coil.CoilImage
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun GitHubRepositoryView(viewModel: GitHubRepositoryViewModel = GitHubRepositoryViewModel()) {

    val scrollState = rememberScrollState()
    val repos by viewModel.repos.observeAsState(listOf())

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        GitHubRepositoryList(repos = repos)
    }
}

@Composable
fun GitHubRepositoryList(repos: List<GitHubRepo>?) {
    Column {
        repos!!.forEach { repo ->
            GitHubRepositoryRow(repo)
        }
    }
}

@Composable
fun GitHubRepositoryRow(repo: GitHubRepo) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        CoilImage(
            imageModel = repo.owner.avatar_url,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .clip(shape = RectangleShape)
                .size(80.dp,80.dp)
                .padding(end = 8.dp)
        )
        Column {
           Text(repo.name, fontWeight = FontWeight.Bold)
           Text("Forks: " + repo.forks_count)
           Text("Watchers: " + repo.watchers_count)
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

        fetchRepos()
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