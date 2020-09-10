package com.bert.studyexoplayer

import android.net.Uri
import android.view.View
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_cover.*
import kotlinx.android.synthetic.main.activity_cover.player_view

class CoverActivity : BaseActivity(R.layout.activity_cover) {

    lateinit var simpleExoPlayer: SimpleExoPlayer
    lateinit var dataSourceFactory: DataSource.Factory

    override fun init() {
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()

        dataSourceFactory =
            DefaultDataSourceFactory(this, Util.getUserAgent(this, "Study-ExoPlayer"))

        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(URLs.gameVideoUrl))

        simpleExoPlayer.prepare(mediaSource)

        simpleExoPlayer.addListener(PlayerEventListener())

        simpleExoPlayer.playWhenReady = true

        player_view?.player = simpleExoPlayer
    }

    override fun initViews() {
        Glide.with(context).load(URLs.gameCoverUrl).into(iv_cover)
    }


    inner class PlayerEventListener:Player.EventListener{
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            when(playbackState){
                Player.STATE_READY -> {
                    iv_cover?.visibility = View.GONE
                }
                Player.STATE_BUFFERING ->{

                }

                Player.STATE_ENDED -> {

                }

                Player.STATE_IDLE -> {

                }

                else -> {
                    
                }
            }
        }
    }
}