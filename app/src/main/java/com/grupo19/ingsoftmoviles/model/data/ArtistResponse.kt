package com.grupo19.ingsoftmoviles.model.data

data class ArtistResponse(
    val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<AlbumResponse>,
    val performerpizes: List<PerformerPrizesResponse>
): java.io.Serializable {
    data class AlbumResponse(
        val id: Int,
        val name: String,
        val cover: String,
        val releaseDate: String,
        val description: String,
        val genre: String,
        val recordLabel: String
    ): java.io.Serializable
    data class PerformerPrizesResponse(
        val id: Int,
        val performerdate: String
    ): java.io.Serializable
}
