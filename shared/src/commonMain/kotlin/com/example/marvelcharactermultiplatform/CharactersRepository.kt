package com.example.marvelcharactermultiplatform

import com.example.marvelcharactermultiplatform.Character

interface CharactersRepository {

    suspend fun getCharacters(timestamp: Long, md5: String): List<Character>

}
