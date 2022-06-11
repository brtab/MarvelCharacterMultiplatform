package com.example.marvelcharactermultiplatform

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorCharacterRepository : CharactersRepository {
    private val baseUrl = "https://gateway.marvel.com/"
    private lateinit var client: HttpClient
    override suspend fun getCharacters(timestamp: Long, md5: String): List<CharacterResult> {
        client = HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    })
            }
        }
        val response = client.get(baseUrl) {
            url {
                appendPathSegments("v1/public/characters")
                headers.append("Accept", "application/json")
                parameters.append("ts", timestamp.toString())
                parameters.append("hash", md5)
                parameters.append("apikey", PublicKey)
            }
        }
        val data: CharactersResponse = response.body()
        return data.data.results
    }
}