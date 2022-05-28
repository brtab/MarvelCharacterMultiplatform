package com.example.marvelcharactermultiplatform.android

interface CharactersRepository {

    suspend fun getCharacters(timestamp: Long, md5: String): List<Character>

}
