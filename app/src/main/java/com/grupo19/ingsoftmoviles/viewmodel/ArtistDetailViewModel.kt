package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.model.repo.ArtistRepository
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import kotlinx.coroutines.launch

class ArtistDetailViewModel(private val artistRepository: ArtistRepository = ArtistRepository()): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _artist = MutableLiveData<ArtistResponse>()
    val artist: LiveData<ArtistResponse> get() = _artist

    fun onCreate(artistId: Int) {
        CountingIdlingResourceSingleton.increment()
        viewModelScope.launch {
            _progressVisible.value = true
            _artist.value = artistRepository.getArtistDetail(artistId)
            _progressVisible.value = false
            CountingIdlingResourceSingleton.decrement()
        }
    }
}