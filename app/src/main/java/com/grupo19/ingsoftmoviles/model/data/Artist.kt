package com.grupo19.ingsoftmoviles.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist_table")
data class Artist (
    @PrimaryKey val id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String
    ): java.io.Serializable