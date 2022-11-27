package com.grupo19.ingsoftmoviles.model.repo

import com.grupo19.ingsoftmoviles.model.data.CollectorResponse
import com.grupo19.ingsoftmoviles.model.network.collector.CollectorServiceAdapter
import com.grupo19.ingsoftmoviles.model.network.collector.CollectorServiceAdapterImpl

class CollectorRepository(private val collectorServiceAdapter: CollectorServiceAdapter = CollectorServiceAdapterImpl()) {
    suspend fun getCollectors(): List<CollectorResponse> = collectorServiceAdapter.getCollectors()
}