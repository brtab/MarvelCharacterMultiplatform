package com.example.marvelcharactermultiplatform

import com.appmattus.crypto.Algorithm
import io.ktor.util.date.*
import io.ktor.utils.io.core.*
import saschpe.kex.Hex

class CharacterClient(
    private val repository: CharactersRepository
) {

    suspend fun getCharacters(): List<CharacterResult> {
        val timestamp = getTimeMillis()
        var characters: List<CharacterResult>
        try {
            characters = repository.getCharacters(
                timestamp,
                md5(timestamp.toString() + PrivateKey + PublicKey)
            )
        } catch(e: Exception) {
            //buscar desde almacenamiento del equipo
            characters = emptyList()
        }
        return sort(characters)
    }

    private fun md5(string: String): String {
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

    internal fun sort(characters: List<CharacterResult>): List<CharacterResult> {
        return characters.sortedWith(CharacterComparator())
    }

    private class CharacterComparator : Comparator<CharacterResult> {
        override fun compare(c1: CharacterResult, c2: CharacterResult): Int {
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