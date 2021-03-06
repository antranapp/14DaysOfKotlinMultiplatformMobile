package app.antran.pexels

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*

class GitHubRepository {

    private var client: HttpClient

    init {
        client = HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                    isLenient = false
                    ignoreUnknownKeys = true
                    allowSpecialFloatingPointValues = true
                    useArrayPolymorphism = false
                })
            }
        }
    }

    suspend fun fetchRepos(): List<GitHubRepo> {
        val url = "https://api.github.com/users/antranapp/repos"
        return client.get<List<GitHubRepo>>(url)
    }
}