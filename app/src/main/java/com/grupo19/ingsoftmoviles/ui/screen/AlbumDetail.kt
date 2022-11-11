package com.grupo19.ingsoftmoviles.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse


@Composable
fun AlbumDetail(scrollState: ScrollState, navController: NavController, album: AlbumResponse){
    Scaffold() {
        Column {
            val image: Painter = rememberAsyncImagePainter(album.cover)
            AlbumHeaderImage(image = image , description = "Album header")
            AlbumTitle(title = album.name, artists = album.performers, genre = album.genre, year = album.releaseDate)
            TrackList(album.tracks)
        }
    }
}

@Composable
fun AlbumHeaderImage(image: Painter, description: String){
    Image(
        painter = image,
        contentDescription = description,
        contentScale = ContentScale.Crop,
        modifier = Modifier.aspectRatio(16f/8f)
    )
}

@Composable
fun AlbumTitle(title: String, artists: List<AlbumResponse.PerformerResponse>, genre: String, year: String){

    val performers = artists.joinToString()

    Column(modifier = Modifier.padding(15.dp)){
        Text(
            text = title,
            fontSize = 20.sp
        )

        Text(
            text = performers,
            fontSize = 20.sp
        )

        Text(
            text = "$genre - $year",
            fontSize = 15.sp
        )
    }

}




@Composable
fun MusicItem(songTitle: String, duration: String) {
    Row(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth(),
        verticalAlignment = CenterVertically
    ) {

        Column(
        ) {


            Row(){
                Column() {
                    Text(text = songTitle)
                }
                Column(modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.End)) {
                    Text(
                        text = duration
                    )
                }

            }

            Divider( thickness = 1.dp)


        }
    }
}

@Composable
fun TrackList(tracks: List<AlbumResponse.TrackResponse>){

    Column(modifier = Modifier.padding(15.dp)){
        Text(
            text = "Tracks",
            fontSize = 20.sp
        )

        tracks.forEach{ track ->
            MusicItem(songTitle = track.name, duration = track.duration)
        }


    }

}