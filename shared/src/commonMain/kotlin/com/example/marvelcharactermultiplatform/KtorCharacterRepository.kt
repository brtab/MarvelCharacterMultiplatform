package com.example.marvelcharactermultiplatform

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class KtorCharacterRepository: CharactersRepository {
    private val baseUrl = "https://gateway.marvel.com/"
    override suspend fun getCharacters(timestamp: Long, md5: String): List<Character> {
        val client = HttpClient()
        val response = client.get(baseUrl) {
            url {
                appendPathSegments("v1/public/characters")
                parameters.append("ts", timestamp.toString())
                parameters.append("hash", md5)
                parameters.append("apikey", PublicKey)
            }
        }
        response.status

        return emptyList()
    }
}