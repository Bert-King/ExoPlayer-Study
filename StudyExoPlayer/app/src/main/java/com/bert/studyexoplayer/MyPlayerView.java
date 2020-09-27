package com.bert.studyexoplayer;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.ui.PlayerView;

/**
 * @Author: bertking
 * @ProjectName: StudyExoPlayer
 * @CreateAt: 2020/9/14 11:13 AM
 * @UpdateAt: 2020/9/14 11:13 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Description:
 */
class MyPlayerView extends PlayerView {

    public MyPlayerView(Context context) {
        this(context,null);
    }

    public MyPlayerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyPlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
