package com.grupo19.ingsoftmoviles.model.network.artist

import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ArtistServiceRetrofit {
    @GET("/musicians")
    suspend fun getArtists(): List<ArtistResponse>
    @GET("/musicians/{artistId}")
    suspend fun getArtistDetail(@Path("artistId") artistId: Int): ArtistResponse
}