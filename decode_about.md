<!--
 * @Author: BertKing
 * @version: 
 * @Date: 2020-09-03 10:48:43
 * @LastEditors: BertKing
 * @LastEditTime: 2020-09-03 11:27:11
 * @FilePath: /ExoPlayer-Study/decode_about.md
 * @Description: 
-->
# 解码(音视频)的相关内容

解码是编码的逆向过程。

---

解码分为两种：
1. **硬解码**:借助于硬件(专用解码芯片)，显然解码速度快有保障，但是兼容性略差。
2. **软解码**:使用解码算法，借助于CPU的计算能力来解码。兼容性好。

---
目前Android开发过程中，[MediaCodec](https://developer.android.google.cn/reference/android/media/MediaCodec) 是Android 4.1(API 16)引入的**硬解码**库。大名鼎鼎的[FFmpeg](http://ffmpeg.org/)是**软解码**库。



