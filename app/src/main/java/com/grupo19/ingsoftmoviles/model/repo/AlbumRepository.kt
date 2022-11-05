package com.grupo19.ingsoftmoviles.model.repo

import com.grupo19.ingsoftmoviles.model.data.Album
import com.grupo19.ingsoftmoviles.model.data.AlbumMapper
import com.grupo19.ingsoftmoviles.model.network.album.AlbumServiceAdapter
import com.grupo19.ingsoftmoviles.model.network.album.AlbumServiceAdapterImpl

class AlbumRepository(private val albumServiceAdapter: AlbumServiceAdapter = AlbumServiceAdapterImpl()) {

    suspend fun getAlbums(): List<Album> = albumServiceAdapter.getAlbums().map(AlbumMapper::transformToAlbum)

}