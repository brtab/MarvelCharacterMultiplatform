package com.example.marvelcharactermultiplatform

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val charactersService: CharacterClient
) {
    private var scope: CoroutineScope = CoroutineScope(Default)
    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val screenState: Flow<ScreenState> = _screenState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        //Log.d("CharactersViewModel", "Error retrieving characters: ${throwable.message}")
    }

    init {
        scope.launch(coroutineExceptionHandler) {
            val list = charactersService.getCharacters()
            _screenState.value = ScreenState.ShowCharacters(list)
        }
    }

    fun clear() {
        scope.cancel()
    }

}

sealed class ScreenState {

    object Loading : ScreenState()

    class ShowCharacters(val list: List<CharacterResult>) : ScreenState()
}