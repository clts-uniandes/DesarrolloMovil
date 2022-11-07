package com.grupo19.ingsoftmoviles.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.gson.Gson
import com.grupo19.ingsoftmoviles.R
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse

class AlbumDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_detail)

        // Get the Intent that started this activity and extract the string
        val album: AlbumResponse = intent.getSerializableExtra(Constants.ALBUM_OBJECT) as AlbumResponse

        println(Gson().toJson(album))
        val textView = findViewById<TextView>(R.id.album_detail).apply {
            text = album.name
        }
    }
}