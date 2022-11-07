package com.grupo19.ingsoftmoviles.model.data

data class TrackResponse(
    val id: Int,
    val name : String,
    val duration : String,
    val album : AlbumTrack

){
    data class AlbumTrack(
        val id: Int,
        val name : String,
        val cover : String,
        val releaseDate : String,
        val description: String,
        val genre: String,
        val recordLabel: String
    )
}