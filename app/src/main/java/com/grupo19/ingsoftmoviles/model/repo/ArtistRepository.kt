package com.grupo19.ingsoftmoviles.model.repo

import com.grupo19.ingsoftmoviles.model.data.ArtistResponse
import com.grupo19.ingsoftmoviles.model.network.artist.ArtistServiceAdapter
import com.grupo19.ingsoftmoviles.model.network.artist.ArtistServiceAdapterImpl

class ArtistRepository(private val artistServiceAdapter: ArtistServiceAdapter = ArtistServiceAdapterImpl()) {
    suspend fun getArtists(): List<ArtistResponse> = artistServiceAdapter.getArtists()
}