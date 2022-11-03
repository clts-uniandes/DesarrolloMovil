package com.grupo19.ingsoftmoviles.uniandes.data.adapters

import com.grupo19.ingsoftmoviles.uniandes.data.AlbumResponse
import retrofit2.http.GET


interface AlbumServiceRetrofit {

    @GET("/albums")
    suspend fun getAlbums(): List<AlbumResponse>

}