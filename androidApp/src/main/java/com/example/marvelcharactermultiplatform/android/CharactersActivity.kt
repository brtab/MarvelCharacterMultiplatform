package com.example.marvelcharactermultiplatform.android

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.marvelcharactermultiplatform.*
import com.example.marvelcharactermultiplatform.android.databinding.ActivityCharactersBinding
import kotlinx.coroutines.launch

class CharactersActivity : AppCompatActivity() {

    private lateinit var charactersAdapter: CharactersAdapter
    lateinit var charactersApiKtor: KtorCharacterRepository
    lateinit var characterClient: CharacterClient
    lateinit var viewModel: CharactersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup del listado
        charactersAdapter = CharactersAdapter()
        val verticalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        with(binding.charactersList) {
            this.adapter = charactersAdapter
            this.layoutManager = verticalLayoutManager
            this.addItemDecoration(VerticalSpaceItemDecoration(16))
        }

        charactersApiKtor = KtorCharacterRepository()
        characterClient = CharacterClient(charactersApiKtor)

        viewModel = CharactersViewModel(characterClient)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.screenState.collect {
                    when (it) {
                        ScreenState.Loading -> showLoading()
                        is ScreenState.ShowCharacters -> showCharacters(it.list)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clear()
    }

    private fun showLoading() {

    }

    private fun showCharacters(list: List<CharacterResult>) {
        charactersAdapter.submitList(list)
    }

}

class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = verticalSpaceHeight
    }

}
