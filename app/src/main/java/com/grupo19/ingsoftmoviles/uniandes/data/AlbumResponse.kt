package com.grupo19.ingsoftmoviles.uniandes.data

data class AlbumResponse(
    val id: Int,
    val name: String,
    val cover: String,
    val releaseDate: String,
    val description: String,
    val genre: String,
    val recordLabel: String,
    val tracks: List<TrackResponse>,
    val performers: List<PerformerResponse>,
    val comments: List<CommentResponse>,
) {

    data class TrackResponse(
        val id: Int,
        val name: String,
        val duration: String
    )

    data class PerformerResponse(
        val id: Int,
        val name: String,
        val description: String,
        val birthDate: String
    )

    data class CommentResponse(
        val id: Int,
        val duration: String,
        val rating: Int
    )
}