package com.android.rickandmorty.ui.episodes

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.rickandmorty.R
import com.android.rickandmorty.inflate
import com.android.rickandmorty.ui.characters.Character


class EpisodeAdapter(private val episodeList: List<Character>) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {


    class EpisodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(
            character: Character
        ) = with(itemView) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder =
        EpisodeViewHolder(parent.inflate(R.layout.item_episode))

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) =
        holder.bind(episodeList[position])

    override fun getItemCount(): Int =
        episodeList.size
}