package com.grupo19.ingsoftmoviles.model.network.artist

import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import retrofit2.http.GET

interface ArtistServiceRetrofit {
    @GET("/musicians")
    suspend fun getArtists(): List<ArtistResponse>
}