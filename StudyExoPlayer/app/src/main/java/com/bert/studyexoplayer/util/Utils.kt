package com.bert.studyexoplayer.util

import android.content.Context
import android.net.TrafficStats
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
object Utils {

    var lastTimeStamp = 0L
    var lastTotalRxBytes = 0L

    /**
     * 获取播放状态的文案
     */
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

    /**
     * 获取网络速度
     * 关于TrafficStats: https://www.cnblogs.com/weilf/p/9180373.html
     * Android 流量统计: https://www.jianshu.com/p/061f8889a888
     */
    fun getNetSpeed(context:Context):Long{
        val uidRxBytes = TrafficStats.getUidRxBytes(context.applicationInfo.uid)
       val nowTotalRxBytes =  if(uidRxBytes.toInt() == TrafficStats.UNSUPPORTED){
            0
        }else{
            TrafficStats.getTotalRxBytes()/1024  // 转为KB
        }

        val nowTimeStamp = System.currentTimeMillis()
        val calculationTime = nowTimeStamp - lastTimeStamp

        if(calculationTime == 0L){
            return calculationTime
        }

        // 毫秒转换
        val speed = (nowTotalRxBytes - lastTotalRxBytes) * 1000 / calculationTime

        lastTimeStamp = nowTimeStamp
        lastTotalRxBytes = nowTotalRxBytes

        return speed
    }

}