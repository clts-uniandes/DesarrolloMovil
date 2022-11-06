package com.grupo19.ingsoftmoviles.model.data

import com.google.gson.Gson
import com.grupo19.ingsoftmoviles.util.FileUtils
import org.junit.Assert
import org.junit.Test

class AlbumMapperTest {

    @Test
    fun transform_album_response_to_album_is_ok() {
        val albumResponse: AlbumResponse = Gson().fromJson(FileUtils.readFile("json/album_response.json"), AlbumResponse::class.java)
        val album:Album = AlbumMapper.transformToAlbum(albumResponse)
        Assert.assertEquals(album.name, "Buscando América")
    }

}