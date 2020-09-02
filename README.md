<!--
 * @Author: BertKing
 * @version: 
 * @Date: 2020-09-02 20:26:00
 * @LastEditors: BertKing
 * @LastEditTime: 2020-09-02 20:51:01
 * @FilePath: /undefined/Users/bertking/Documents/GitHub/ExoPlayer-Study/README.md
 * @Description: 
-->
# ExoPlayer-Study
伴随着ExoPlayer应用到项目中，如今研究并记录一些日常开发点滴...

---
现在客户端开发中，随着哔哩哔哩的 [ijkplayer](https://github.com/bilibili/ijkplayer)不再有人维护，业内的播放器大家都会优先考虑Google推出的 [ ExoPlayer ](https://github.com/google/ExoPlayer).
> [GSYVideoPlayer
](https://github.com/CarGuo/GSYVideoPlayer)是基于两者开发而成的，大家也可以尝试一下。



目前爱奇艺也是基于[ ExoPlayer ](https://github.com/google/ExoPlayer)封装的，采用Google家的相对有保证。正如标题所言，本项目的主角只有一个：ExoPlayer

---

### 关于常见需求的说明
1. 经常逛Youtube的朋友都知道，我们拖拽进度条的时候，会出现对应位置视频的缩略图(主流的播放器都有该功能)，我ExoPlayer库不支持该功能，需要自己实现。详情请参考: [Preview seekbar like youtube ](https://github.com/google/ExoPlayer/issues/5254)

2. [画中画功能(PIP:Picture-in-Picture)](https://developer.android.com/guide/topics/ui/picture-in-picture#java)
