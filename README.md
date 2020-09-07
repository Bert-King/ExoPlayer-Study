<!--
 * @Author: BertKing
 * @version: 
 * @Date: 2020-09-02 20:26:00
 * @LastEditors: BertKing
 * @LastEditTime: 2020-09-03 15:12:56
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
##  1. ExoPlayer的概论

1. [ExoPlayer的Github地址](https://github.com/google/ExoPlayer)
2. [开发者文档](https://exoplayer.dev/hello-world.html)
3. [ExoPlayer库中类文件说明文档](https://exoplayer.dev/doc/reference/)

### ExoPlayer中的Media Sources
正如其 [文档](https://exoplayer.dev/media-sources.html) 上所言，在 ExoPlayer 中所有媒体文件都被**MediaSource类**所表示。另外ExoPlayer库内置了支持常见流类型的MediaSource的实现。
1. DashMediaSource for [DASH(MPEG DASH，扩展名：mpd)](https://exoplayer.dev/dash.html).
2. SsMediaSource for [SmoothStreaming 全称是:Microsoft Smooth Streaming,又简记为:MSS](https://exoplayer.dev/smoothstreaming.html).
3. HlsMediaSource for [HLS(HTTP Live Streaming 扩展名:m3u8 苹果公司出品](https://exoplayer.dev/hls.html).
4. ProgressiveMediaSource for [常规的媒体文件](https://exoplayer.dev/progressive.html).


## 2. 流媒体类型的历史

最初，流媒体协议也是呈现三足鼎立之势，分别是Adobe家的[HDS-HTTP Dynamic Streaming](https://www.encoding.com/http-dynamic-streaming-hds/),微软家的[(M)SS- Microsoft Smooth Streaming](https://www.encoding.com/microsoft-smooth-streaming-mss/)以及苹果家的[HLS-HTTP Live Streaming](https://www.encoding.com/http-live-streaming-hls/). 每家都有自己的协议和格式，为了结束这种混乱的局面，[MPEG-DASH](https://www.encoding.com/mpeg-dash/)应运而生。


---

ExoPlayer为我们提供了工具类用于判断视频类型，使我们方便地选出正确的MediaSource类型。
```Java
  /**
     * 
     * @param uri 媒体文件的链接
     * @param extension 扩展名
     * @param drmSessionManager 版权管理(DRM:数字版权管理)
     * @return 相应的MediaSource
     */
 private MediaSource createLeafMediaSource(
            Uri uri, String extension, DrmSessionManager<?> drmSessionManager) {
        @ContentType int type = Util.inferContentType(uri, extension);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(dataSourceFactory)
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(uri);
            case C.TYPE_SS:
                return new SsMediaSource.Factory(dataSourceFactory)
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(uri);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(dataSourceFactory)
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(uri);
            case C.TYPE_OTHER:
                return new ProgressiveMediaSource.Factory(dataSourceFactory)
                        .setDrmSessionManager(drmSessionManager)
                        .createMediaSource(uri);
            default:
                throw new IllegalStateException("Unsupported type: " + type);
        }
    }
```

我们紧接着来看一下Util.inferContentType()的实现,其实现逻辑还是很清晰的。

```Java
 /**
   * Makes a best guess to infer the type from a file name.
   *
   * @param fileName Name of the file. It can include the path of the file.
   * @return The content type.
   */
  @C.ContentType
  public static int inferContentType(String fileName) {
    fileName = toLowerInvariant(fileName);
    if (fileName.endsWith(".mpd")) {
      return C.TYPE_DASH;
    } else if (fileName.endsWith(".m3u8")) {
      return C.TYPE_HLS;
    }
    Matcher ismMatcher = ISM_URL_PATTERN.matcher(fileName);
    if (ismMatcher.matches()) {
      @Nullable String extensions = ismMatcher.group(2);
      if (extensions != null) {
        if (extensions.contains(ISM_DASH_FORMAT_EXTENSION)) {
          return C.TYPE_DASH;
        } else if (extensions.contains(ISM_HLS_FORMAT_EXTENSION)) {
          return C.TYPE_HLS;
        }
      }
      return C.TYPE_SS;
    }
    return C.TYPE_OTHER;
  }
```



除了上面提到的MediaSource，ExoPlayer库还为我们提供了
* ConcatenatingMediaSource : 用于播放列表(支持添加，删除)
* ClippingMediaSource: 用于裁剪，只播放视频的一部分
* LoopingMediaSource:循环播放(推荐使用ExoPlayer的setRepeatMode)
* MergingMediaSource:加载字幕文件





PS: 建议运行Demo来理解。


---
### 关于常见需求的说明
1. 经常逛Youtube的朋友都知道，我们拖拽进度条的时候，会出现对应位置视频的缩略图(主流的播放器都有该功能)，我ExoPlayer库不支持该功能，需要自己实现。详情请参考: [Preview seekbar like youtube ](https://github.com/google/ExoPlayer/issues/5254)

2. [画中画功能(PIP:Picture-in-Picture)](https://developer.android.com/guide/topics/ui/picture-in-picture#java)




深入学习音视频开发的推荐链接:

1. [Android音视频开发学习指南](https://zhuanlan.zhihu.com/p/28518637)
2. [Android音视频开发学习系列文章](https://juejin.im/post/6844903949451919368)
3. [ExoPlayer剖析](https://www.jianshu.com/p/f506c279e4e5)