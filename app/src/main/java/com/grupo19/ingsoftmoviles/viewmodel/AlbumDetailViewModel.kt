package com.grupo19.ingsoftmoviles.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.repo.AlbumRepository
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import kotlinx.coroutines.launch

class AlbumDetailViewModel(private val albumRepository: AlbumRepository = AlbumRepository()): ViewModel() {

    var progressVisible: MutableState<Boolean> = mutableStateOf(true)

    var album: MutableState<AlbumResponse> = mutableStateOf(AlbumResponse(0, "", "", "", "", "", "", emptyList(), emptyList(), emptyList()))

    fun onCreate(albumId: Int) {
        CountingIdlingResourceSingleton.increment()
        viewModelScope.launch {
            if(album.value.id == 0) {
                album.value = albumRepository.getAlbumDetail(albumId)
            }
            progressVisible.value = false
            CountingIdlingResourceSingleton.decrement()
        }
    }
}