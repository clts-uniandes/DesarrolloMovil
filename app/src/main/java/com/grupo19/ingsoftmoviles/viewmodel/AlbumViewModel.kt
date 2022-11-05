package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.Album
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlbumViewModel(): ViewModel() {

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
            _albums.value = getAlbums()
            _progressVisible.value = false
        }
    }

    fun getAlbums():List<Album> {
        return listOf(Album(1,"Album1", "Cover1", "Performer1"), Album(2,"Album2", "Cover2", "Performer2"))
    }

    fun onAlbumClick(album: Album) {
        //TODO("Setear detalle de album aca")
        _showMessage.value = album.name
    }
}