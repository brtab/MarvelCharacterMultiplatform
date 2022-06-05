package com.example.marvelcharactermultiplatform.android

import androidx.recyclerview.widget.RecyclerView
import com.example.marvelcharactermultiplatform.CharacterResult
import com.example.marvelcharactermultiplatform.android.databinding.ListItemCharacterBinding
import com.squareup.picasso.Picasso

class CharacterViewHolder(private val binding: ListItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterResult) {
        binding.name.text = character.name
        binding.description.text = character.description
        if (character.thumbnail.toUrl().isNotEmpty()) {
            Picasso.get()
                .load(character.thumbnail.toUrl())
                .into(binding.image)
        } else {
            binding.image.setImageURI(null)
        }
    }

}
