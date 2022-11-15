package com.grupo19.ingsoftmoviles.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.model.repo.ArtistRepository
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import kotlinx.coroutines.*

class ArtistViewModel (private val artistRepository: ArtistRepository = ArtistRepository()): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _artists = MutableLiveData<List<ArtistResponse>>()
    val artists: LiveData<List<ArtistResponse>> get() = _artists
    var job: Job? = null

    fun onCreate() {
        Log.d("Thread Outside", Thread.currentThread().name)

        job = CoroutineScope(Dispatchers.IO).launch {
            Log.d("Thread Inside", Thread.currentThread().name)

            val response = artistRepository.getArtists()
            withContext(Dispatchers.Main) {
                if (response.isNotEmpty()) {
                    _progressVisible.value = true
                    _artists.value = response
                    _progressVisible.value = false
                } else {
                    Log.d("Error ", "error ejecutando consulta")
                }
            }
        }
    }

}