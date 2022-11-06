package com.grupo19.ingsoftmoviles.model.network.album

import com.google.gson.Gson
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.model.network.RetrofitBuilder
import com.grupo19.ingsoftmoviles.util.FileUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.*

class AlbumServiceAdapterImplTest {

    @Test
    fun get_albums_is_ok()  = runBlocking {
        val retrofitBuilderMock : RetrofitBuilder = mock(RetrofitBuilder::class.java)
        val albumServiceMock: AlbumServiceRetrofit = mock(AlbumServiceRetrofit::class.java)
        val albumServiceAdapter: AlbumServiceAdapter = AlbumServiceAdapterImpl(retrofitBuilderMock)
        val albumResponseList: List<AlbumResponse> = listOf(Gson().fromJson(FileUtils.readFile("json/album_response.json"), AlbumResponse::class.java))

        `when`(retrofitBuilderMock.createClient(AlbumServiceRetrofit::class.java)).thenReturn(albumServiceMock)
        `when`(albumServiceMock.getAlbums()).thenReturn(albumResponseList)

        val albums = albumServiceAdapter.getAlbums()

        Assert.assertEquals(albums[0].name, "Buscando América")
    }
}