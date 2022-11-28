package com.grupo19.ingsoftmoviles.ui.screen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.ui.Constants
import com.grupo19.ingsoftmoviles.ui.album.AsociateTrackAlbumActivity


@Composable
fun AlbumDetail(scrollState: ScrollState, navController: NavController, album: AlbumResponse){
    Scaffold() {
        Column {
            val image: Painter = rememberAsyncImagePainter(album.cover)
            AlbumHeaderImage(image = image , description = "Album header")
            AlbumTitle(title = album.name, artists = album.performers, genre = album.genre, year = album.releaseDate)
            TrackList(album.tracks)
            AssociateTrackButton(album.id)
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

@Composable
fun AssociateTrackButton(id: Int){
    Column(modifier = Modifier.padding(15.dp).fillMaxWidth().wrapContentWidth(Alignment.End)){
        val context = LocalContext.current
        val intent = Intent(context, AsociateTrackAlbumActivity::class.java)
        intent.putExtra(Constants.ALBUM_ID, id)

        Button(
            onClick = { context.startActivity(intent) },
            // Uses ButtonDefaults.ContentPadding by default
            contentPadding = PaddingValues(
                start = 20.dp,
                top = 12.dp,
                end = 20.dp,
                bottom = 12.dp
            )
        ) {
            // Inner content including an icon and a text label
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("AGREGAR TRACK")
        }

    }
}