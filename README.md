<!--
 * @Author: BertKing
 * @version: 
 * @Date: 2020-09-02 20:26:00
 * @LastEditors: BertKing
 * @LastEditTime: 2020-09-03 14:06:52
 * @FilePath: /ExoPlayer-Study/README.md
 * @Description: 
-->
# ExoPlayer-Study
伴随着ExoPlayer应用到项目中，如今研究并记录一些日常开发点滴...

---
现在客户端开发中，随着哔哩哔哩的 [ijkplayer](https://github.com/bilibili/ijkplayer)不再有人维护，业内的播放器大家都会优先考虑Google推出的 [ ExoPlayer ](https://github.com/google/ExoPlayer).
> [GSYVideoPlayer
](https://github.com/CarGuo/GSYVideoPlayer)是基于两者开发而成的，大家也可以尝试一下。



目前爱奇艺,Youtube都是基于[ ExoPlayer ](https://github.com/google/ExoPlayer)封装的，采用Google家的相对有保证。正如标题所言，本项目的主角只有一个：ExoPlayer

---
##  ExoPlayer的概论

1. [ExoPlayer的Github地址](https://github.com/google/ExoPlayer)
2. [开发者文档](https://exoplayer.dev/hello-world.html)
3. [ExoPlayer库中类文件说明文档](https://exoplayer.dev/doc/reference/)

### ExoPlayer中的Media Sources
正如其 [文档](https://exoplayer.dev/media-sources.html) 上所言，在 ExoPlayer 中所有媒体文件都被**MediaSource类**所表示。另外ExoPlayer库内置了支持常见流类型的MediaSource的实现。
1. DashMediaSource for [DASH](https://exoplayer.dev/dash.html).
2. SsMediaSource for [SmoothStreaming](https://exoplayer.dev/smoothstreaming.html).
3. HlsMediaSource for [HLS(HTTP Live Streaming)](https://exoplayer.dev/hls.html).
4. ProgressiveMediaSource for [常规的媒体文件](https://exoplayer.dev/progressive.html).







PS: 建议运行Demo来理解。


---
### 关于常见需求的说明
1. 经常逛Youtube的朋友都知道，我们拖拽进度条的时候，会出现对应位置视频的缩略图(主流的播放器都有该功能)，我ExoPlayer库不支持该功能，需要自己实现。详情请参考: [Preview seekbar like youtube ](https://github.com/google/ExoPlayer/issues/5254)

2. [画中画功能(PIP:Picture-in-Picture)](https://developer.android.com/guide/topics/ui/picture-in-picture#java)




深入学习音视频开发的推荐链接:

1. [Android音视频开发学习指南](https://zhuanlan.zhihu.com/p/28518637)
2. [Android音视频开发学习系列文章](https://juejin.im/post/6844903949451919368)