package com.lukdro.dermediaplayer.data

interface IMusicRepo {
    operator fun get(index: Int): Song
    fun size(): Int
}