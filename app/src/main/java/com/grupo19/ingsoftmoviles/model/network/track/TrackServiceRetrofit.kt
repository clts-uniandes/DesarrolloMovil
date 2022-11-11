package com.grupo19.ingsoftmoviles.model.network.track

import com.grupo19.ingsoftmoviles.model.data.TrackCreate
import com.grupo19.ingsoftmoviles.model.data.TrackResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface TrackServiceRetrofit{

    @POST("/albums/{id_album}/tracks")
    suspend fun creteTrack(@Path("id_album")  id_album:Int, @Body trackCreate: TrackCreate): TrackResponse

}