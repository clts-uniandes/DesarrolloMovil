package com.grupo19.ingsoftmoviles.model.network.artist

import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.model.network.RetrofitBuilder
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ArtistServiceAdapterImpl( private val clientBuilder: RetrofitBuilder = RetrofitBuilder()):ArtistServiceAdapter {
    override suspend fun getArtists(): List<ArtistResponse> {
        return clientBuilder.createClient(ArtistServiceRetrofit::class.java).getArtists()
    }
}