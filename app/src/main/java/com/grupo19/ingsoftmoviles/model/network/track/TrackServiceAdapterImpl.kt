package com.grupo19.ingsoftmoviles.model.network.track

import com.grupo19.ingsoftmoviles.model.data.TrackCreate
import com.grupo19.ingsoftmoviles.model.network.RetrofitBuilder

class TrackServiceAdapterImpl(private val clientBuilder: RetrofitBuilder = RetrofitBuilder()):TrackServiceAdapter {
    override fun createTrack(id_album: Int, trackCreate: TrackCreate): Boolean {
        val client = clientBuilder.createClient(TrackServiceRetrofit::class.java)
        val response = client.creteTrack(id_album, trackCreate)
        if (response.id == null){
            return false
        }
        return true
    }
}