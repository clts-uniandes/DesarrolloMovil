package com.grupo19.ingsoftmoviles.model.network.album

import com.grupo19.ingsoftmoviles.model.data.AlbumCreate
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import retrofit2.Response

interface AlbumServiceAdapter {

    suspend fun getAlbums(): List<AlbumResponse>
    suspend fun createAlbum(newAlbum: AlbumCreate): Response<AlbumResponse>

}