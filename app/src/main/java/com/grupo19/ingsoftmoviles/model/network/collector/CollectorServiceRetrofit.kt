package com.grupo19.ingsoftmoviles.model.network.collector

import com.grupo19.ingsoftmoviles.model.data.CollectorResponse
import retrofit2.http.GET

interface CollectorServiceRetrofit {

    @GET("/collectors")
    suspend fun getCollectors(): List<CollectorResponse>

}