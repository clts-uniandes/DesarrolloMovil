package com.grupo19.ingsoftmoviles.model.repo

import com.grupo19.ingsoftmoviles.model.data.AlbumCreate
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.network.album.AlbumServiceAdapter
import com.grupo19.ingsoftmoviles.model.network.album.AlbumServiceAdapterImpl
import retrofit2.Response

class AlbumRepository(private val albumServiceAdapter: AlbumServiceAdapter = AlbumServiceAdapterImpl()) {

    suspend fun getAlbums(): List<AlbumResponse> = albumServiceAdapter.getAlbums()

    suspend fun getAlbumDetail(albumId: Int): AlbumResponse = albumServiceAdapter.getAlbumDetail(albumId)

    suspend fun createAlbum(albumCreate: AlbumCreate): Response<AlbumResponse>? {
        return albumServiceAdapter.createAlbum(albumCreate)
    }

}