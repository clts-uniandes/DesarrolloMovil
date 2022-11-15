package com.grupo19.ingsoftmoviles.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse

@Dao
interface ArtistsResponse {
    @Query("SELECT * FROM artists_table")
    fun getArtists():List<ArtistResponse>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(artist: ArtistResponse)

    @Query("DELETE FROM artists_table")
    suspend fun deleteAll()
}