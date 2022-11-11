package com.grupo19.ingsoftmoviles.model

sealed class ResultWrapper<out T> {
    data class Success<out T>(val data: T? = null) : ResultWrapper<T>()
    data class Loading(val nothing: Nothing?=null) : ResultWrapper<Nothing>()
    data class Error(val msg: String?) : ResultWrapper<Nothing>()
}