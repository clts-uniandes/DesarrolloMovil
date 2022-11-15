package com.grupo19.ingsoftmoviles.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artists_table")
data class ArtistResponse(
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    val albums: List<AlbumResponse>,
    val performerpizes: List<PerformerPrizesResponse>
){
    data class AlbumResponse(
        val id: Int,
        val name: String,
        val cover: String,
        val releaseDate: String,
        val description: String,
        val genre: String,
        val recordLabel: String
    )
    data class PerformerPrizesResponse(
        val id: Int,
        val performerdate: String
    )
}
