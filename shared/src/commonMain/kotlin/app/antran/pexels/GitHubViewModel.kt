package app.antran.pexels

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch

class GitHubViewModel: ViewModel() {
    private var _repos = MutableLiveData<List<GitHubRepo>>(listOf())
    var repos: LiveData<List<GitHubRepo>> = _repos

    private val repository = GitHubRepository()

    fun fetchRepos() {
        viewModelScope.launch {
            _repos.value = repository.fetchRepos()
        }
    }
}