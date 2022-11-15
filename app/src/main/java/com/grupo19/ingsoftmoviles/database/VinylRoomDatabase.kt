package com.grupo19.ingsoftmoviles.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.grupo19.ingsoftmoviles.database.dao.ArtistsResponse
import com.grupo19.ingsoftmoviles.model.data.ArtistResponse

@Database(entities = [ArtistResponse::class], version = 1, exportSchema = false)
abstract class VinylRoomDatabase:RoomDatabase() {
    abstract fun artistResponseDao(): ArtistsResponse

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: VinylRoomDatabase? = null

        fun getDatabase(context: Context): VinylRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VinylRoomDatabase::class.java,
                    "vinyls_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}