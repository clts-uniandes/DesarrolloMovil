package com.grupo19.ingsoftmoviles.model.data

data class CollectorResponse(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val comments: List<Comment>,
    val favoritePerformers: List<Artist>,
    val collectorAlbums: List<CollectorAlbum>
) {
    data class CollectorAlbum(
        val id: Int, val price: Double, val status: String
    )
}
