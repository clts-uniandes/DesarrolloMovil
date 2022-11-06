package com.grupo19.ingsoftmoviles.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.Album
import com.grupo19.ingsoftmoviles.model.repo.AlbumRepository
import kotlinx.coroutines.launch

class AlbumViewModel(private val albumRepository: AlbumRepository = AlbumRepository()): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> get() = _albums

    //TODO("Esto se debe reemplazar por el detalle de un album")
    private val _showMessage = MutableLiveData<String>()
    val showMessage: LiveData<String> get() = _showMessage

    fun onCreate() {
        viewModelScope.launch {
            _progressVisible.value = true
            _albums.value = albumRepository.getAlbums()
            _progressVisible.value = false
        }
    }

    fun onAlbumClick(album: Album) {
        //TODO("Setear detalle de album aca")
        _showMessage.value = album.name
    }
}