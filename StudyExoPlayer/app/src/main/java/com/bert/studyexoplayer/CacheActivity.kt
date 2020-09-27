package com.bert.studyexoplayer

import android.net.Uri
import com.bert.studyexoplayer.util.URLs
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.database.DatabaseProvider
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import kotlinx.android.synthetic.main.activity_cache.*
import java.io.File

/**
 * 主要用于展示ExoPlayer配置缓存的演示
 * evictor:驱逐者，清除者，收回者，常见于英文关于Cache的文档
 */
class CacheActivity : BaseActivity(R.layout.activity_cache) {

    lateinit var simpleExoPlayer: SimpleExoPlayer

    lateinit var cacheDataSourceFactory: CacheDataSourceFactory

    lateinit var simpleCache: SimpleCache

    lateinit var evictor: LeastRecentlyUsedCacheEvictor

    lateinit var databaseProvider: DatabaseProvider




    override fun init() {
        simpleExoPlayer = SimpleExoPlayer.Builder(this).build()

        // 指定缓存文件夹
        val cacheFile = File(this.cacheDir, "media")

        // 指定缓存大小以及移除策略
        evictor = LeastRecentlyUsedCacheEvictor(100 * 1024 * 1024)

        databaseProvider = ExoDatabaseProvider(this)

        // 建立缓存
        simpleCache = SimpleCache(cacheFile, evictor, databaseProvider)

        // 建立 开启缓存的Data source factory,如果数据在缓存中可用，则直接返回，否则将会打开一个新的链接去拉取数据
        cacheDataSourceFactory = CacheDataSourceFactory(
            simpleCache,
            DefaultHttpDataSourceFactory("ExoPlayer")
        )

        val uri = Uri.parse(URLs.m3u8Url)
        val mediaSource = HlsMediaSource.Factory(cacheDataSourceFactory)
            .createMediaSource(uri)

        simpleExoPlayer.prepare(mediaSource)
        player_view?.player = simpleExoPlayer
        simpleExoPlayer.playWhenReady = true
    }


    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        simpleCache.release()
    }

    override fun initViews() {
        btn_evictor?.setOnClickListener {

        }
    }



}