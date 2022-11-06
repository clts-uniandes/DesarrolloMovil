package com.grupo19.ingsoftmoviles.util

class FileUtils {

    companion object {
        fun readFile(path: String): String {
            return ClassLoader.getSystemClassLoader().getResourceAsStream(path).bufferedReader().use{it.readText()}
        }
    }

}