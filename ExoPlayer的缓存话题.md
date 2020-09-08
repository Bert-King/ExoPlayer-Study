<!--
 * @Author: BertKing
 * @version: 
 * @Date: 2020-09-08 21:12:27
 * @LastEditors: BertKing
 * @LastEditTime: 2020-09-08 21:19:01
 * @FilePath: /ExoPlayer-Study/ExoPlayer的缓存话题.md
 * @Description: 
-->

> 这里先简单记录一下:

**CacheSpan**是Cache数据的最小单元。

**SimpleCache** 代表着缓存数据。

**CacheEvictor**代表着缓存移除数据的策略。


**CacheDataSourceFactory**带缓存的DataSourceFactory。

1. LeastRecentlyUsedCacheEvictor :LRU策略
2. NoOpCacheEvictor:不会清除缓存文件的策略


[Using cache in ExoPlayer](https://stackoverflow.com/questions/28700391/using-cache-in-exoplayer/31913519)

