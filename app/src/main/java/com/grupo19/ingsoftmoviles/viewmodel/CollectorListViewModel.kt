package com.grupo19.ingsoftmoviles.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grupo19.ingsoftmoviles.model.data.CollectorResponse
import com.grupo19.ingsoftmoviles.model.repo.CollectorRepository
import com.grupo19.ingsoftmoviles.ui.CountingIdlingResourceSingleton
import kotlinx.coroutines.launch

class CollectorListViewModel(private val collectorRepository: CollectorRepository = CollectorRepository()): ViewModel() {

    private val _progressVisible = MutableLiveData<Boolean>()
    val progressVisible: LiveData<Boolean> get() = _progressVisible

    private val _collectors = MutableLiveData<List<CollectorResponse>>()
    val collectors: LiveData<List<CollectorResponse>> get() = _collectors

    private val _showSelectedCollector = MutableLiveData<CollectorResponse>()
    val showSelectedCollector: LiveData<CollectorResponse> get() = _showSelectedCollector

    fun onCreate() {
        CountingIdlingResourceSingleton.increment()
        viewModelScope.launch {
            _progressVisible.value = true
            _collectors.value = collectorRepository.getCollectors()
            _progressVisible.value = false
            CountingIdlingResourceSingleton.decrement()
        }
    }

    fun onCollectorClick(collector: CollectorResponse) {
        _showSelectedCollector.value = collector //pendiente solo pasar id de coleccionista
    }

}
