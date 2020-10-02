package com.android.rickandmorty.ui.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.rickandmorty.api.ApiClient
import com.android.rickandmorty.api.MyResult
import com.android.rickandmorty.ui.locations.Location
import kotlinx.coroutines.launch

class LocationsViewModel : ViewModel() {

    private val _locations = MutableLiveData<MyResult<List<Location>>>()
    val location: LiveData<MyResult<List<Location>>> = _locations

    fun loadLocations() {
        viewModelScope.launch {
            _locations.value = try {
                val locations = ApiClient.client.getLocations().results
                MyResult.Success(locations)
            } catch (e: Throwable) {
                MyResult.Failure(e)
            }
        }

    }
}
