package com.grupo19.ingsoftmoviles.model.repo

import com.grupo19.ingsoftmoviles.model.data.TrackCreate
import com.grupo19.ingsoftmoviles.model.network.track.TrackServiceAdapter
import com.grupo19.ingsoftmoviles.model.network.track.TrackServiceAdapterImpl

class TrackRepository(private val trackServiceAdapter: TrackServiceAdapter = TrackServiceAdapterImpl()) {
    fun createTrack(id_album:Int, trackCreate: TrackCreate): Boolean {
        return trackServiceAdapter.createTrack(id_album, trackCreate)
    }
}