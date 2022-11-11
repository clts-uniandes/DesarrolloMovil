package com.grupo19.ingsoftmoviles.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.Gson
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.ui.VinilosApp
import com.grupo19.ingsoftmoviles.ui.theme.VinilosTheme

class MainActivity2: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val album: AlbumResponse = intent.getSerializableExtra(Constants.ALBUM_OBJECT) as AlbumResponse
        println(Gson().toJson(album))
        setContent {
            VinilosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    VinilosApp(album=album)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    VinilosTheme {

    }
}