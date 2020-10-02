package com.android.rickandmorty.ui.episodes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.rickandmorty.R


class EpisodesFragment : Fragment() {

    private lateinit var episodesViewModel: EpisodesViewModel
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        episodesViewModel =
                ViewModelProviders.of(this).get(EpisodesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_episodes, container, false)
        val textView: TextView = root.findViewById(R.id.text_episodes)
        episodesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        setUpRecyclerView(root)
        episodesViewModel.loadEpisode()
        return root
    }

    private fun setUpRecyclerView(root: View) {
            viewManager = LinearLayoutManager(context)

        }


}
