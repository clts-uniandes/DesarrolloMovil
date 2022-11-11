package com.grupo19.ingsoftmoviles.model.network.album

import com.grupo19.ingsoftmoviles.model.data.AlbumCreate
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AlbumServiceRetrofit {

    @GET("/albums")
    suspend fun getAlbums(): List<AlbumResponse>

    @POST("/albums")
    suspend fun createAlbum(@Body albumCreate: AlbumCreate): Response<AlbumResponse>

}