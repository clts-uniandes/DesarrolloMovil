package com.grupo19.ingsoftmoviles.ui.album

import androidx.compose.foundation.rememberScrollState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import com.grupo19.ingsoftmoviles.model.data.AlbumResponse
import com.grupo19.ingsoftmoviles.ui.screen.AlbumDetail

@Composable
fun VinilosApp(albumId:Int) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    AlbumDetail(scrollState = scrollState, navController = navController, albumId = albumId)
}