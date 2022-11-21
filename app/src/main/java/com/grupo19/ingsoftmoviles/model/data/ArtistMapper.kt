package com.grupo19.ingsoftmoviles.model.data

class ArtistMapper {
    companion object{
        fun transformToArtist(artistResponse: ArtistResponse):Artist{
            return Artist(artistResponse.id, artistResponse.name, artistResponse.image, artistResponse.description, artistResponse.birthDate)
        }
    }
}