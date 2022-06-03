package com.example.marvelcharactermultiplatform

import kotlinx.serialization.Serializable

@Serializable
class Character(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnailUrl: String
)