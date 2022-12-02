package com.grupo19.ingsoftmoviles.ui.album

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.grupo19.ingsoftmoviles.ui.Constants
import com.grupo19.ingsoftmoviles.ui.theme.VinilosTheme

class AlbumDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val albumId: Int = intent.extras?.getInt(Constants.ALBUM_ID)?.toInt() ?: Constants.ALBUM_ID_ERROR
        println("Este es el albumID: $albumId")
        setContent {
            VinilosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    VinilosApp(albumId=albumId)
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