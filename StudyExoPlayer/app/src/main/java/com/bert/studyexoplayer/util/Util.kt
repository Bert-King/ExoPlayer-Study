package com.bert.studyexoplayer.util

import com.google.android.exoplayer2.Player

/**
 *
 * @Author: bertking
 * @ProjectName: StudyExoPlayer
 * @CreateAt: 2020/11/24 9:20 PM
 * @UpdateAt: 2020/11/24 9:20 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 * @Description:
 */
object Util {

    @JvmStatic
    fun getPlaybackStateText(state: Int): String {
        return when (state) {
            Player.STATE_READY -> {
                "准备就绪"
            }

            Player.STATE_BUFFERING -> {
                "缓冲中"
            }

            Player.STATE_ENDED -> {
                "播放结束"
            }

            Player.STATE_IDLE -> {
                "空闲..."
            }

            else -> {
                ""
            }
        }
    }
}