package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.model.repo.ArtistRepository
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import kotlinx.coroutines.launch

class ArtistViewModel (private val artistRepository: ArtistRepository = ArtistRepository()): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _artists = MutableLiveData<List<ArtistResponse>>()
    val artists: LiveData<List<ArtistResponse>> get() = _artists

    private val _showArtistSelected = MutableLiveData<ArtistResponse>()
    val showArtistDetail: LiveData<ArtistResponse> get() = _showArtistSelected

    fun onCreate() {
        CountingIdlingResourceSingleton.increment()
        viewModelScope.launch {
            _progressVisible.value = true
            _artists.value = artistRepository.getArtists()
            _progressVisible.value = false
            CountingIdlingResourceSingleton.decrement()
        }
    }

    fun onArtistClick(artist: ArtistResponse) {
        _showArtistSelected.value = artist
    }

}