package com.bert.studyexoplayer

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.drm.DrmSessionManager
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName

    lateinit var simpleExoPlayer: SimpleExoPlayer
    lateinit var dataSourceFactory:DataSource.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()

        player_view?.player = simpleExoPlayer


        dataSourceFactory = DefaultDataSourceFactory(this,Util.getUserAgent(this,"Study-ExoPlayer"))

        val mp4MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(URLs.mp4Url))
        simpleExoPlayer.prepare(mp4MediaSource)
        simpleExoPlayer.playWhenReady= true

    }





    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}