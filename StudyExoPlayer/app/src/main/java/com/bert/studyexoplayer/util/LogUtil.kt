package com.bert.studyexoplayer.util

import com.google.android.exoplayer2.util.Log

/**
 *
 * @Author: bertking
 * @ProjectName: StudyExoPlayer
 * @CreateAt: 2020/9/10 2:45 PM
 * @UpdateAt: 2020/9/10 2:45 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Description:
 */
object LogUtil {
    const val isDebug = true

    @JvmStatic
    fun debug(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}