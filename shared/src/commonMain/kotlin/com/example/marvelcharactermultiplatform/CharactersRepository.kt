package com.example.marvelcharactermultiplatform

interface CharactersRepository {

    suspend fun getCharacters(timestamp: Long, md5: String): List<CharacterResult>

}
