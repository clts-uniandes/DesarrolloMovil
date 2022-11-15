package com.grupo19.ingsoftmoviles
import android.app.Application
import com.grupo19.ingsoftmoviles.database.VinylRoomDatabase

class VindaloosApplication: Application(){
    val database by lazy { VinylRoomDatabase.getDatabase(this) }
}