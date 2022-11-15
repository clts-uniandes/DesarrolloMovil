package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.*
import com.grupo19.ingsoftmoviles.database.VinylRoomDatabase
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.model.repo.ArtistRepository
import com.grupo19.ingsoftmoviles.ui.ArtistsListActivity
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import kotlinx.coroutines.launch

class ArtistViewModel(application: ArtistsListActivity): ViewModel()  {
    private val ArtistRepository = ArtistRepository(application, VinylRoomDatabase.getDatabase(application.applicationContext).artistResponseDao())
    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _artists = MutableLiveData<List<ArtistResponse>>()
    val artists: LiveData<List<ArtistResponse>> get() = _artists
    init {
        onCreate()
    }
    fun onCreate() {
        CountingIdlingResourceSingleton.increment()
        viewModelScope.launch {
            _progressVisible.value = true
            _artists.value = ArtistRepository.getArtists()
            _progressVisible.value = false
            CountingIdlingResourceSingleton.decrement()
        }
    }

    class Factory(val app: ArtistsListActivity) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ArtistViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }


}