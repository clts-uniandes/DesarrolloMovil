package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.CollectorResponse
import com.grupo19.ingsoftmoviles.model.repo.CollectorRepository
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import kotlinx.coroutines.launch

class CollectorDetailViewModel(private val collectorRepository: CollectorRepository = CollectorRepository()): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _collector = MutableLiveData<CollectorResponse>()
    val collector: LiveData<CollectorResponse> get() = _collector

    fun onCreate(collectorId: Int) {
        CountingIdlingResourceSingleton.increment()
        viewModelScope.launch {
            _progressVisible.value = true
            _collector.value = collectorRepository.getCollectorDetail(collectorId)
            _progressVisible.value = false
            CountingIdlingResourceSingleton.decrement()
        }
    }
}