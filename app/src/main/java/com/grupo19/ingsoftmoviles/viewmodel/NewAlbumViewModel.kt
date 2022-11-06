package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.*
import com.grupo19.ingsoftmoviles.model.data.AlbumCreate
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.repo.AlbumRepository
import kotlinx.coroutines.launch

class NewAlbumViewModel(private val albumRepository: AlbumRepository = AlbumRepository()): ViewModel() {

    private val creationResult: MutableLiveData<AlbumResponse> = MutableLiveData()

    fun createAlbum(name: String, cover: String, releaseDate: String, description: String, genre: String, recordLabel: String) {
        val newAlbum = AlbumCreate(name, cover, releaseDate, description, genre, recordLabel)
        viewModelScope.launch {
            val albumResult = albumRepository.createAlbum(newAlbum)
            if (albumResult?.code() == 200) {
                creationResult.value = albumResult.body()
            }
        }
    }


}