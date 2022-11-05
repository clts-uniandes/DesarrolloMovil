package com.grupo19.ingsoftmoviles.model.data

class AlbumMapper {

    companion object {
        fun transformToAlbum(albumResponse: AlbumResponse): Album {
            return Album(albumResponse.id, albumResponse.name, albumResponse.cover, "albumResponse.getPerformersName()")
        }
    }
}