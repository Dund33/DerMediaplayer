package com.lukdro.dermediaplayer.data

class MusicRepo: IMusicRepo {
    private val tunes = listOf(
        Song("You Know You're right", "file1.mp3"),
        Song("Would", "Would.mp3"),
        Song("In Bloom", "InBloom.mp3"),
        Song("Erika", "erika.mp3"),
        Song("On A Plain", "plain.mp3")
    )
    override fun get(index: Int): Song = tunes[index]
    override fun size(): Int = tunes.size
}