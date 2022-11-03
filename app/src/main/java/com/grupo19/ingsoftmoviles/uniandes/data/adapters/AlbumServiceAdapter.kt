package com.grupo19.ingsoftmoviles.uniandes.data.adapters

import com.grupo19.ingsoftmoviles.uniandes.data.AlbumResponse

interface AlbumServiceAdapter {

    suspend fun getAlbums(): List<AlbumResponse>

}