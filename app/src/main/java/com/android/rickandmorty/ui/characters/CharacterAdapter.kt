package com.android.rickandmorty.ui.characters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.rickandmorty.R
import com.android.rickandmorty.inflate
import com.android.rickandmorty.loadImage
import kotlinx.android.synthetic.main.item_character.view.*

class CharacterAdapter(private val characters: List<Character>,
private val characterListClickHandler :CharacterListClickHandler) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            character: Character,
            characterListClickHandler: CharacterListClickHandler
        ) = with(itemView) {
            character_name.text = character.name
            character_species.text = when {
                character.type.isNotEmpty() -> "${character.species} - ${character.type}"
                else -> character.species
            }
            loadImage(character_img, character.image, itemView.context)
            character_status.text = character.status

            itemView.setOnClickListener{
                characterListClickHandler.onItemClick(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(parent.inflate(R.layout.item_character))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) =
        holder.bind(characters[position],characterListClickHandler)

    override fun getItemCount(): Int =
        characters.size
}