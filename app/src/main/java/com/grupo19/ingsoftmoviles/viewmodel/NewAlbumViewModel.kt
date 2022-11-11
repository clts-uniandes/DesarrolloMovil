package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.*
import com.grupo19.ingsoftmoviles.model.ResultWrapper
import com.grupo19.ingsoftmoviles.model.data.AlbumCreate
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.repo.AlbumRepository
import kotlinx.coroutines.launch

class NewAlbumViewModel(private val albumRepository: AlbumRepository = AlbumRepository()): ViewModel() {

    val creationResult: MutableLiveData<ResultWrapper<AlbumResponse>> = MutableLiveData()

    fun createAlbum(name: String, cover: String, releaseDate: String, description: String, genre: String, recordLabel: String) {
        val newAlbum = AlbumCreate(name, cover, releaseDate, description, genre, recordLabel)
        creationResult.value = ResultWrapper.Loading()
        viewModelScope.launch {
            try {
                val result = albumRepository.createAlbum(newAlbum)
                if (result?.code() == 200) {
                    creationResult.value = ResultWrapper.Success(result.body())
                }
                else{
                    creationResult.value = ResultWrapper.Error(result?.message())
                }
            } catch (ex: Exception) {
                creationResult.value = ResultWrapper.Error(ex.message)
            }


        }
    }


}