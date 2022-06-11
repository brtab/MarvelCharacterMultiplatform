package com.example.marvelcharactermultiplatform

import com.example.marvelcharactermultiplatform.CharacterResult
import com.example.marvelcharactermultiplatform.CharacterClient
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersService: CharacterClient
) {

    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val screenState: Flow<ScreenState> = _screenState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        //Log.d("CharactersViewModel", "Error retrieving characters: ${throwable.message}")
    }

    init {
        val scope = CoroutineScope(Default)
        scope.launch(coroutineExceptionHandler) {
            val list = charactersService.getCharacters()
            _screenState.value = ScreenState.ShowCharacters(list)
        }
    }

}

sealed class ScreenState {

    object Loading : ScreenState()

    class ShowCharacters(val list: List<CharacterResult>) : ScreenState()
}