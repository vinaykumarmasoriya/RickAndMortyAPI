package com.android.rickandmorty.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.rickandmorty.api.ApiClient
import com.android.rickandmorty.api.MyResult
import com.android.rickandmorty.api.MyResult.Failure
import com.android.rickandmorty.api.MyResult.Success
import com.android.rickandmorty.ui.characters.Character
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private val _characters = MutableLiveData<MyResult<List<Character>>>()
    val moreCharacters: LiveData<MyResult<List<Character>>> = _characters
    private val client = ApiClient.client
    private var page = 1
    private var pages = Int.MAX_VALUE

    fun loadMoreCharacters() {
        if (page <= pages)
            viewModelScope.launch {
                _characters.value = try {
                    with(client.getCharacters(page++)) {
                        pages = info.pages
                        Success(results)
                    }
                } catch (e: Throwable) {
                    Log.e("GET CHARACTERS", e.toString())
                    Failure(e)
                }
            }
    }
}
