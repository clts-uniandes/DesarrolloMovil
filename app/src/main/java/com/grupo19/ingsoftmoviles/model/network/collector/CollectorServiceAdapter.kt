package com.grupo19.ingsoftmoviles.model.network.collector

import com.grupo19.ingsoftmoviles.model.data.CollectorResponse

interface CollectorServiceAdapter {

    suspend fun getCollectors(): List<CollectorResponse>

}