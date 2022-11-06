package com.grupo19.ingsoftmoviles.model.network.album

import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import retrofit2.http.GET

interface AlbumServiceRetrofit {

    @GET("/albums")
    suspend fun getAlbums(): List<AlbumResponse>

}