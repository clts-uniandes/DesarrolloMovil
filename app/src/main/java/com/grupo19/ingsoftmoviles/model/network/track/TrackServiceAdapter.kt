package com.grupo19.ingsoftmoviles.model.network.track

import com.grupo19.ingsoftmoviles.model.data.TrackCreate

interface TrackServiceAdapter {

    suspend fun createTrack(id_album: Int, trackCreate: TrackCreate): Boolean

}