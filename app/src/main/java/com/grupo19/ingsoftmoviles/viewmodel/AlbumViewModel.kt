package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.repo.AlbumRepository
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumRepository: AlbumRepository = AlbumRepository()): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _albums = MutableLiveData<List<AlbumResponse>>()
    val albums: LiveData<List<AlbumResponse>> get() = _albums

    private val _showAlbumSelected = MutableLiveData<AlbumResponse>()
    val showMessage: LiveData<AlbumResponse> get() = _showAlbumSelected

    fun onCreate() {
        CountingIdlingResourceSingleton.increment()
        viewModelScope.launch {
            _progressVisible.value = true
            _albums.value = albumRepository.getAlbums()
            _progressVisible.value = false
            CountingIdlingResourceSingleton.decrement()
        }
    }

    fun onAlbumClick(album: AlbumResponse) {
        _showAlbumSelected.value = album
    }
}