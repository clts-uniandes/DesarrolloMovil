package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.TrackCreate
import com.grupo19.ingsoftmoviles.model.repo.TrackRepository
import kotlinx.coroutines.launch

class TrackViewModel(private val trackRepository: TrackRepository = TrackRepository()): ViewModel() {
    private val _statusMessage = MutableLiveData<Boolean>()

    val statusMessage: LiveData<Boolean> get() = _statusMessage

    fun onCreateTrack(id_album:Int, trackCreate: TrackCreate){
        viewModelScope.launch {
            _statusMessage.value = trackRepository.createTrack(id_album, trackCreate)
        }
    }
}