package com.bert.studyexoplayer

import android.content.Intent
import android.net.Uri
import android.util.Log
import com.bert.studyexoplayer.util.URLs
import com.bert.studyexoplayer.util.Utils
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_main.*

/**
 * evictor:驱逐者，清除者，收回者，常见于英文关于Cache的文档
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    lateinit var simpleExoPlayer: SimpleExoPlayer
    lateinit var dataSourceFactory: DataSource.Factory

    override fun init() {
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()

        dataSourceFactory =
            DefaultDataSourceFactory(this, Util.getUserAgent(this, "Study-ExoPlayer"))

        initPlayer()
    }

    override fun initViews() {
        tv_speed?.text = Utils.getNetSpeed(context).toString()


        btn_cache?.setOnClickListener {
            startActivity(Intent(context, CacheActivity::class.java))
        }

        btn_cover?.setOnClickListener {
            startActivity(Intent(context,CoverActivity::class.java))
        }
    }

    private fun initPlayer() {
        val mp4MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(URLs.mp4Url))
        simpleExoPlayer.prepare(mp4MediaSource)
        simpleExoPlayer.playWhenReady = false
        player_view?.player = simpleExoPlayer
    }


    override fun onResume() {
        super.onResume()
        Log.d(TAG, "------onResume-------")
        if (Util.SDK_INT <= 23) {
//            initPlayer()
            player_view?.onResume()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"------onStart-------")
        if (Util.SDK_INT > 23) {
//            initPlayer()
            player_view?.onResume()
            simpleExoPlayer.playWhenReady = true
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "------onPause-------")

        if (Util.SDK_INT <= 23) {
            player_view?.onPause()
            simpleExoPlayer.stop()
        }

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "------onStop-------")
        if (Util.SDK_INT > 23) {
            player_view?.onPause()
            simpleExoPlayer.playWhenReady = false
//            simpleExoPlayer.release()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "------onDestroy-------")
        player_view?.overlayFrameLayout?.removeAllViews()
        simpleExoPlayer.release()
    }
}