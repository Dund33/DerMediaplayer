package com.lukdro.dermediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lukdro.dermediaplayer.data.IMusicRepo
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class TuneFragment : Fragment(R.layout.tune) {

    private val args: TuneFragmentArgs by navArgs()

    @Inject
    lateinit var repo: IMusicRepo
    private var mediaPlayer: MediaPlayer = MediaPlayer()
    private var tuneId by Delegates.notNull<Int>()
    private val tenSeconds = 10000

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.songTitle)
        val playButton = view.findViewById<Button>(R.id.playButton)
        val backwardButton = view.findViewById<Button>(R.id.zuruck10)
        val forwardButton = view.findViewById<Button>(R.id.voraus10)
        val nextSong = view.findViewById<Button>(R.id.nextSong)
        val prevSong = view.findViewById<Button>(R.id.prevSong)

        tuneId = args.tuneId
        textView.text = repo[tuneId].name
        playButton.setOnClickListener { playSong(tuneId) }
        backwardButton.setOnClickListener { backward10() }
        forwardButton.setOnClickListener { forward10() }
        prevSong.setOnClickListener { previousSong() }
        nextSong.setOnClickListener { nextSong() }

    }

    fun playSong(tuneId: Int) {
        val playButton = view?.findViewById<Button>(R.id.playButton)
        playButton?.text = getString(R.string.pause)
        playButton?.setOnClickListener { pauseSong() }
        val file = File(Environment.getExternalStorageDirectory(), repo[tuneId].file)
        mediaPlayer.setDataSource(file.path)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    private fun pauseSong() {
        val playPauseButton = view?.findViewById<Button>(R.id.playButton)
        playPauseButton?.text = getString(R.string.resume)
        playPauseButton?.setOnClickListener { resumeSong() }
        mediaPlayer.pause()
    }

    private fun resumeSong() {
        val playButton = view?.findViewById<Button>(R.id.playButton)
        playButton?.text = getString(R.string.pause)
        playButton?.setOnClickListener { pauseSong() }
        mediaPlayer.start()
    }

    private fun forward10() {
        mediaPlayer.seekTo(
            when (mediaPlayer.currentPosition + tenSeconds > mediaPlayer.duration) {
                true -> 0
                false -> mediaPlayer.currentPosition + tenSeconds
            }
        )
    }

    private fun backward10() {
        mediaPlayer.seekTo(
            when (mediaPlayer.currentPosition - tenSeconds < 0) {
                true -> 0
                false -> mediaPlayer.currentPosition - tenSeconds
            }
        )
    }


    private fun nextSong() {
        rebuildMediaPlayer()
        val textView = view?.findViewById<TextView>(R.id.songTitle)
        tuneId = when (tuneId + 1 >= repo.size()) {
            true -> 0
            false -> tuneId + 1
        }
        textView?.text = repo[tuneId].name
        playSong(tuneId)
    }

    private fun previousSong() {
        rebuildMediaPlayer()
        val textView = view?.findViewById<TextView>(R.id.songTitle)
        tuneId = when (tuneId - 1 < 0) {
            true -> 0
            false -> tuneId - 1
        }
        textView?.text = repo[tuneId].name
        playSong(tuneId)
    }

    private fun rebuildMediaPlayer() {
        mediaPlayer.stop()
        mediaPlayer.release()
        mediaPlayer = MediaPlayer()
    }
}