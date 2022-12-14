package com.grupo19.ingsoftmoviles.model.network.album

import com.grupo19.ingsoftmoviles.model.data.AlbumCreate
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.network.RetrofitBuilder
import retrofit2.Response

class AlbumServiceAdapterImpl(private val clientBuilder: RetrofitBuilder = RetrofitBuilder()): AlbumServiceAdapter {

    override suspend fun getAlbums(): List<AlbumResponse> {
        return clientBuilder.createClient(AlbumServiceRetrofit::class.java).getAlbums()
    }

    override suspend fun getAlbumDetail(albumId: Int): AlbumResponse {
        return clientBuilder.createClient(AlbumServiceRetrofit::class.java).getAlbumDetail(albumId)
    }

    override suspend fun createAlbum(newAlbum: AlbumCreate): Response<AlbumResponse> {
        val client = clientBuilder.createClient(AlbumServiceRetrofit::class.java)
        return client.createAlbum(newAlbum)
    }
}