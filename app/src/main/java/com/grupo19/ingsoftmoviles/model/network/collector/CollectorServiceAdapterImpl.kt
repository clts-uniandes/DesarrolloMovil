package com.grupo19.ingsoftmoviles.model.network.collector

import com.grupo19.ingsoftmoviles.model.data.CollectorResponse
import com.grupo19.ingsoftmoviles.model.network.RetrofitBuilder
import com.grupo19.ingsoftmoviles.model.network.artist.ArtistServiceRetrofit

class CollectorServiceAdapterImpl(private val clientBuilder: RetrofitBuilder = RetrofitBuilder()): CollectorServiceAdapter {

    override suspend fun getCollectors(): List<CollectorResponse> {
        return clientBuilder.createClient(CollectorServiceRetrofit::class.java).getCollectors()
    }

    override suspend fun getCollectorDetail(collectorId: Int): CollectorResponse {
        return clientBuilder.createClient(CollectorServiceRetrofit::class.java).getCollectorDetail(collectorId)
    }

}