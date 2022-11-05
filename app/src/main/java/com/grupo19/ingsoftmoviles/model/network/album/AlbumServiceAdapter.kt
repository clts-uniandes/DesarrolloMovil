package com.grupo19.ingsoftmoviles.model.network.album

import com.grupo19.ingsoftmoviles.model.data.AlbumResponse

interface AlbumServiceAdapter {

    suspend fun getAlbums(): List<AlbumResponse>

}