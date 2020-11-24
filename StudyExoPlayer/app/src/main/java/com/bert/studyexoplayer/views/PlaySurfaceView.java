package com.bert.studyexoplayer.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.bert.studyexoplayer.util.URLs;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.video.VideoListener;

/**
 * @Author: bertking
 * @ProjectName: VideoPlayerTest
 * @CreateAt: 2020/11/23 9:00 PM
 * @UpdateAt: 2020/11/23 9:00 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Description:
 */
public class PlaySurfaceView extends SurfaceView implements SurfaceHolder.Callback, VideoListener, Player.EventListener {
    public static final String TAG = "PlaySurfaceView";

    private SurfaceHolder surfaceHolder;

    private Canvas canvas;

    private Paint paint;

    public SimpleExoPlayer getSimpleExoPlayer() {
        return simpleExoPlayer;
    }

    private SimpleExoPlayer simpleExoPlayer;

    private int videoWidth, videoHeight;

    public PlaySurfaceView(Context context) {
        this(context, null);
    }

    public PlaySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlaySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        Log.d(TAG, "======当前所在线程:======" + Thread.currentThread().getName());

        init();
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        setZOrderOnTop(true);

        simpleExoPlayer = new SimpleExoPlayer.Builder(getContext()).build();


        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "=========surfaceCreated==========");
        prepare();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d(TAG, "=========surfaceChanged==========width:" + width + ",height:" + height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "=========surfaceDestroyed==========");

    }

    public void prepare() {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(), "VideoPlayerTest");
        Uri uri = Uri.parse(URLs.mp4Url);
        ProgressiveMediaSource mp4MediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
        simpleExoPlayer.setVideoSurfaceHolder(getHolder());
        simpleExoPlayer.prepare(mp4MediaSource);
        simpleExoPlayer.setForegroundMode(true);


//        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addVideoListener(this);
        simpleExoPlayer.addListener(this);
    }


    public String getPlaybackStateText( int playbackState) {
        String text = "";
        switch (playbackState) {
            case Player.STATE_READY:
                /* The player is able to immediately play from its current position */
                text = "准备就绪";
                break;
            case Player.STATE_BUFFERING:
                /*
                 The player is not able to immediately play from its current position.This state typically
                    occurs when more data needs to be loaded.
                */
                text = "缓冲中";
                break;
            case Player.STATE_ENDED:
                /* The player has finished playing the media */
                text = "播放结束";
                break;

            case Player.STATE_IDLE:
                /* The player does not have any media to play. */
                text = "空闲";
                break;
        }
        return text;
    }


    /***** VideoListener ****/


    @Override
    public void onVideoSizeChanged(int width, int height, int unappliedRotationDegrees, float pixelWidthHeightRatio) {
        Log.d(TAG, "onVideoSizeChanged 视频宽高 width:" + width + ",height:" + height);
        videoWidth = width;
        videoHeight = height;


    }

    /**
     * 注意此方法在第一次不会被调用
     */
    @Override
    public void onSurfaceSizeChanged(int width, int height) {
        Log.d(TAG, "onSurfaceSizeChanged width:" + width + ",height:" + height);

    }

    @Override
    public void onRenderedFirstFrame() {
        Log.d(TAG, "onRenderedFirstFrame");
    }

    /***** EventListener ****/

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        Log.d(TAG, "onPlayerError:" + error.getMessage());
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        // 1. 缓冲过程：STATE_BUFFERING -》STATE_READY
        // 2. 在Player.REPEAT_MODE_ALL过程中，除了第一次会触发Player.STATE_READY(播放过程中，可能也会触发『缓冲过程』)，之后在切换的时候会调用onRenderedFirstFrame()方法。

        // 3.  exoPlayer.setPlayWhenReady(true/false);直接影响到 playWhenReady这个参数，其也是区分stop() 和 pause()的关键之所在。

        // 4. 未设置RepeatMode的情况下，然后使用seekTo(0)的方式进行重播，要区分两种情况：
        // 4.1 正在播放：Player.STATE_BUFFERING -> Player.STATE_READY
        // 4.2 暂停情况下: Player.STATE_BUFFERING(false) -> Player.STATE_BUFFERING(true) -> Player.STATE_READY

        // 5. 播放结束则会调用：Player.STATE_ENDED, 如果后面一直没有操作，大概持续一段时间(about 10min)，进入Player.STATE_IDLE


        /**
         * 关于stop(）和 resume()的问题：
         *
         * 1. 调用  exoPlayer.stop(reset = false); 则ExoPlayer会立即进入到 Player.STATE_IDLE的空闲状态。
         *
         * 在空闲状态，意味着 Player 没有可以播放在资源，所以 exoPlayer.setPlayWhenReady(true/false) 以及 player.seekTo(0)
         * 等操作都将无效。
         *
         * 故只能重新:setMediaSource(MediaSource) and prepare() 操作，必须调用prepare()，否则不会播放
         *
         */


        Log.d(TAG, "onPlayerStateChanged : 开始播放:" + playWhenReady + ",播放状态:" + getPlaybackStateText(playbackState));

    }


}
