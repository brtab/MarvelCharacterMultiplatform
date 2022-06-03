package com.example.marvelcharactermultiplatform

import com.appmattus.crypto.Algorithm
import io.ktor.util.date.*
import io.ktor.utils.io.core.*
import saschpe.kex.Hex

class CharacterClient(
    private val repository: CharactersRepository
) {

    suspend fun getAllCharacters(): List<Character> {
        val timestamp = getTimeMillis()
        val characters = repository.getCharacters(
            timestamp,
            md5(timestamp.toString() + PrivateKey + PublicKey)
        )
        return sort(characters)
    }

    private fun md5(string: String): String {
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest = Algorithm.MD5.createDigest()
            digest.update(string.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = Hex.toHexString(messageDigest)
            return hexString
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    private fun sort(characters: List<Character>): List<Character> {
        return characters.sortedWith(CharacterComparator())
    }

    private class CharacterComparator : Comparator<Character> {
        override fun compare(c1: Character, c2: Character): Int {
            if (c1.description.isEmpty() && c2.description.isEmpty()) {
                return c2.id.compareTo(c1.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isNotEmpty()) {
                return c1.id.compareTo(c2.id)
            }
            if (c1.description.isNotEmpty() && c2.description.isEmpty()) {
                return -1
            }
            return 1
        }

    }
}