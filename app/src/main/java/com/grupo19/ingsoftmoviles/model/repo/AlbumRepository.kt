package com.grupo19.ingsoftmoviles.model.repo

import com.grupo19.ingsoftmoviles.model.data.Album
import com.grupo19.ingsoftmoviles.model.data.AlbumCreate
import com.grupo19.ingsoftmoviles.model.data.AlbumMapper
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.network.album.AlbumServiceAdapter
import com.grupo19.ingsoftmoviles.model.network.album.AlbumServiceAdapterImpl
import retrofit2.Response

class AlbumRepository(private val albumServiceAdapter: AlbumServiceAdapter = AlbumServiceAdapterImpl()) {

    suspend fun getAlbums(): List<Album> = albumServiceAdapter.getAlbums().map(AlbumMapper::transformToAlbum)

    suspend fun createAlbum(albumCreate: AlbumCreate): Response<AlbumResponse>? {
        return albumServiceAdapter.createAlbum(albumCreate)
    }

}