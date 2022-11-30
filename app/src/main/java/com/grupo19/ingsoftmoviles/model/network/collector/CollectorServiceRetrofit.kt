package com.grupo19.ingsoftmoviles.model.network.collector

import com.grupo19.ingsoftmoviles.model.data.CollectorResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CollectorServiceRetrofit {

    @GET("/collectors")
    suspend fun getCollectors(): List<CollectorResponse>
    @GET("/collectors/{collectorId}")
    suspend fun getCollectorDetail(@Path("collectorId") artistId: Int): CollectorResponse

}