package com.grupo19.ingsoftmoviles.model.network.artist

import com.grupo19.ingsoftmoviles.model.data.ArtistResponse

interface ArtistServiceAdapter {
    suspend fun getArtists(): List<ArtistResponse>
}