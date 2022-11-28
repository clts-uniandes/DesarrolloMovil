package com.grupo19.ingsoftmoviles.model.network.album

import com.grupo19.ingsoftmoviles.model.data.AlbumCreate
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AlbumServiceRetrofit {

    @GET("/albums")
    suspend fun getAlbums(): List<AlbumResponse>

    @GET("/albums/{albumId}")
    suspend fun getAlbumDetail(@Path("albumId")  albumId:Int): AlbumResponse

    @POST("/albums")
    suspend fun createAlbum(@Body albumCreate: AlbumCreate): Response<AlbumResponse>

}