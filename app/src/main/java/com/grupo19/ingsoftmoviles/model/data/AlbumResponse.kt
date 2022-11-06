package com.grupo19.ingsoftmoviles.model.data

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

    fun getPerformersName(): String {
        return performers.joinToString("-")
    }

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
    ) {

        override fun toString(): String {
            return name
        }
    }

    data class CommentResponse(
        val id: Int,
        val duration: String,
        val rating: Int
    )
}