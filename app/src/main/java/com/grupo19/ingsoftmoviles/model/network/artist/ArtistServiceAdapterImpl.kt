package com.grupo19.ingsoftmoviles.model.network.artist

import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.model.network.RetrofitBuilder

class ArtistServiceAdapterImpl( private val clientBuilder: RetrofitBuilder = RetrofitBuilder()):ArtistServiceAdapter {
    override suspend fun getArtists(): List<ArtistResponse> {
        return clientBuilder.createClient(ArtistServiceRetrofit::class.java).getArtists()
    }
}