package com.bert.studyexoplayer.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @Author: bertking
 * @ProjectName: VideoPlayerTest
 * @CreateAt: 2020/11/23 8:03 PM
 * @UpdateAt: 2020/11/23 8:03 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Description: 当SurfaceView不可见时,会立即销毁SurfaceView的SurfaceHolder.
 */
public class MySurfaceView extends SurfaceView {
    public static final String TAG = "MySurfaceView";

    private SurfaceHolder surfaceHolder;

    private Canvas canvas;

    private Paint paint;

    public MySurfaceView(Context context) {
        this(context, null, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Log.d(TAG, "=========surfaceCreated==========");

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        draw();
                    }
                }).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Log.d(TAG, "=========surfaceChanged==========");

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Log.d(TAG, "=========surfaceDestroyed==========");

            }
        });

        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }


    private void draw(){
        try {
            Log.d(TAG,"=======draw()========");
            canvas = surfaceHolder.lockCanvas();
            canvas.drawCircle(500,500,200,paint);
            canvas.drawCircle(100,100,50,paint);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(canvas != null){
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }



}
