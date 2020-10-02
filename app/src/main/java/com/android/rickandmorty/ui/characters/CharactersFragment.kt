package com.android.rickandmorty.ui.characters

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.rickandmorty.R
import com.android.rickandmorty.api.MyResult
import com.android.rickandmorty.api.MyResult.Success
import com.android.rickandmorty.onEndScroll
import kotlinx.android.synthetic.main.fragment_characters.view.*

class CharactersFragment : Fragment() {

    private lateinit var viewModel: CharactersViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val characters = mutableListOf<Character>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(CharactersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_characters, container, false)

        setUpRecyclerView(root)
        viewModel.loadMoreCharacters()
        viewModel.moreCharacters.observe(viewLifecycleOwner, Observer(this::processMoreCharacters))
        return root
    }

    private fun processMoreCharacters(result: MyResult<List<Character>>) {
        if (result is Success) addCharacters(result.data)
    }

    private fun addCharacters(moreCharacters: List<Character>) {
        characters.addAll(moreCharacters)
        viewAdapter.notifyDataSetChanged()
    }

    private fun setUpRecyclerView(root: View) {
        viewManager = LinearLayoutManager(context)
        viewAdapter = CharacterAdapter(characters,object :CharacterListClickHandler{
            override fun onItemClick(character: Character) {
                showDialog(character)
            }

        })
        recyclerView = root.characters_recycler_view.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        recyclerView.onEndScroll(viewModel::loadMoreCharacters)
    }

    private fun showDialog(character: Character) {
        val dialog = buildDialog(R.layout.location_dialog)
        val okBtn = dialog.findViewById<Button>(R.id.ok_btn)
        val location = dialog.findViewById<TextView>(R.id.location_Sub_title_txt)
        location.text = character.location.name
        dialog.show()
        dialog.setCanceledOnTouchOutside(false)
        okBtn.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun buildDialog(layoutID: Int): Dialog {
        return requireContext().locationDialog(layoutID)
    }

    private fun Context.locationDialog(@LayoutRes layoutId: Int): Dialog {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layoutId)
        dialog.window!!.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_corner))
        return dialog
    }
}
