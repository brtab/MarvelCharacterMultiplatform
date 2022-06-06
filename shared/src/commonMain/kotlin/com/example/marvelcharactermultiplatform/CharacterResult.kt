package com.example.marvelcharactermultiplatform

import kotlinx.serialization.Serializable

@Serializable
data class CharacterResult(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail
)

@Serializable
data class CharactersResponse(
    val data: CharacterData
)

@Serializable
data class CharacterData(
    val results: List<CharacterResult>
)

@Serializable
data class Thumbnail(
    val path : String,
    val extension : String
) {
    fun toUrl() = "$path.$extension"
}