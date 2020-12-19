package com.bert.studyexoplayer

import android.net.Uri
import com.bert.studyexoplayer.util.URLs
import com.bert.studyexoplayer.util.Utils
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_cover.*
import kotlinx.android.synthetic.main.activity_cover.player_view

/**
 * 模拟线上项目
 *
 * 带有封面的播放器
 */
class CoverActivity : BaseActivity(R.layout.activity_cover) {

    lateinit var simpleExoPlayer: SimpleExoPlayer
    lateinit var dataSourceFactory: DataSource.Factory

    var isReleased = false

    override fun init() {
        initPlayer()
    }

    private fun initPlayer() {
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

    override fun onPause() {
        player_view?.onPause()
        simpleExoPlayer.stop()
        super.onPause()
        debug("onPause")
    }


    override fun onResume() {
        super.onResume()
        if(isReleased){
            initPlayer()
        }
        player_view?.onResume()
        debug("onResume:${Util.SDK_INT}")
    }

    override fun onStop() {
        super.onStop()
        debug("onStop")
        simpleExoPlayer.release()
        isReleased = true
    }

    /**
     * 播放相关的监听事件
     */
    inner class PlayerEventListener : Player.EventListener {

        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            debug(Utils.getPlaybackStateText(playbackState))
        }

        override fun onLoadingChanged(isLoading: Boolean) {
            debug("onLoadingChanged:$isLoading")
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            debug("onPlayerError:${error.message}")
        }
    }
}