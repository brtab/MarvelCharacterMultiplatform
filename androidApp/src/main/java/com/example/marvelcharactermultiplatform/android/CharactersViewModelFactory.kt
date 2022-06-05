package com.example.marvelcharactermultiplatform.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.marvelcharactermultiplatform.CharacterClient
import com.example.marvelcharactermultiplatform.KtorCharacterRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val charactersApiKtor = KtorCharacterRepository()
        val characterClient = CharacterClient(charactersApiKtor)

        return CharactersViewModel(characterClient) as T
    }
}