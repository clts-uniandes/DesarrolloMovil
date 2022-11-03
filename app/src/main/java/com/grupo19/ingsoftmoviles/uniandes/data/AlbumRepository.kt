package com.grupo19.ingsoftmoviles.uniandes.data

import com.grupo19.ingsoftmoviles.uniandes.data.adapters.AlbumServiceAdapter
import com.grupo19.ingsoftmoviles.uniandes.data.adapters.AlbumServiceAdapterImpl

class AlbumRepository(private val albumServiceAdapter: AlbumServiceAdapter = AlbumServiceAdapterImpl()) {

    suspend fun getAlbums(): List<Album> = albumServiceAdapter.getAlbums().map(AlbumMapper::transformToAlbum)

}