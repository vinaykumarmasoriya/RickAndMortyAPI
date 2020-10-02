package com.android.rickandmorty.ui.episodes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.rickandmorty.api.ApiClient
import com.android.rickandmorty.api.MyResult
import com.android.rickandmorty.ui.characters.Character
import kotlinx.coroutines.launch

class EpisodesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is episodes Fragment"
    }
    val text: LiveData<String> = _text
    private val _characters = MutableLiveData<MyResult<List<Character>>>()
    private val client = ApiClient.client
    private var page = 1
    private var pages = Int.MAX_VALUE

    fun loadEpisode() {
        if (page <= pages)
            viewModelScope.launch {
                _characters.value = try {
                    with(client.getCharacters(page++)) {
                        pages = info.pages
                        MyResult.Success(results)
                    }
                } catch (e: Throwable) {
                    Log.e("GET CHARACTERS", e.toString())
                    MyResult.Failure(e)
                }
            }
    }
}