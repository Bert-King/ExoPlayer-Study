<!--
 * @Author: BertKing
 * @version: 
 * @Date: 2020-09-07 11:04:49
 * @LastEditors: BertKing
 * @LastEditTime: 2020-09-07 16:53:32
 * @FilePath: /ExoPlayer-Study/关键概念.md
 * @Description: URI 和 URL的理解
-->
> **URL** 和 **URI**是我们日常开发经常接触到的，对于Android开发而言，**Uri.parse(url)** 更是司空见惯。但是你知道两者的区别与联系吗？

URI：Uniform Resource Identifier 统一资源**标识**符

URL：Uniform Resource Locator 统一资源**定位**符


---

# 第一部分:URI 的理解

[统一资源标识符(URI)的定义](https://www.ietf.org/rfc/rfc2396.txt) 



>A Uniform Resource Identifier (URI) is a compact string of characters
   for identifying an abstract or physical resource. 

> 是用于识别抽象或者具体的资源的一个紧凑字符串，包括绝对和相对两种形式。


[URI - 文档描述](https://developer.android.com/reference/java/net/URI) 加深对于URI的理解。

[Java Class URI](https://docs.oracle.com/javase/10/docs/api/java/net/URI.html)

## URI的语法与组件

URI 的scheme 定义了 URI的命名空间(namespace)，关于命名空间的作用在这里不再赘述。

按照是否存在**scheme**可以将URI分为**绝对形式**和**相对形式**,具体内容如下：

### 1. URI的绝对形式

```Java
<scheme>:<scheme-specific-part>
```
需要我们注意的是,URI语法并不对**scheme-specific-part**部分有什么限制。但是为了表示在命名空间方面的层次关系，通用的URI进一步划分为如下结构:
```Java
<scheme>://<authority><path>?<query>
```
---
##### 小插曲

URL根据scheme-specific-part是否以反斜杠(/)开头，将URI分为**Hierarchical(层次的)** 和 **Opaque(透明的)**。

需要注意的是:**URI的相对形式一直都是Hierarchical(层次的)**

> 1. news:comp.lang.java
> 2. mailto:nobody@google.com
>
>这些都属于Opaque URI。不需要进一步解析。



---
除了**scheme**外，每个部分都有可能缺失。
```Java
absoluteURI = scheme:(hier_part | opaque_part)

hier_part = (net_path | abs_path) [ "?" query ]

net_path = "//" authority [ abs_path ]

abs_path = "/" path_segments


authority = [user-info @] host [:port]

特地声明一下:中括号[]的内容可有可无

```
关于URI的组件的获取，[Java Class URI](https://docs.oracle.com/javase/10/docs/api/java/net/URI.html) 提供了详细的指导。建议结合着源码和具体的URI运行分析。




### 2. URI的相对形式

**URI的相对形式**允许文档树不依赖于资源的位置和Scheme进行寻址。

>A hierarchical URI is either an absolute URI whose scheme-specific part begins with a slash character, or a relative URI, that is, a URI that does not specify a scheme.

```Java
 relativeURI   = ( net_path | abs_path | rel_path ) [ "?" query ]

 rel_path      = rel_segment [ abs_path ]

```
很显然，**没有指定Scheme的都是URI的相对格式**。

---

# 第二部分:URL 的理解

URL：Uniform Resource Locator 统一资源**定位**符

URN:Uniform Resource Name 统一资源名称

[URL-文档描述](https://developer.android.com/reference/java/net/URL)

URI帮我们确定资源后，我们就可以对该资源进行多种操作:访问，更新，替换以及查找属性。所以URI进一步分为:定位器(Locator)，名称(Name)或兼而有之。

这就是URL 和 URN的由来。

* URL 是 URI的子集：根据其访问权限来标识资源。

* URN 是 URI的子集：保持资源的全局唯一性和持久性(即使资源不存在 or 不可用)


----

# 最后

[RFC 2396: Uniform Resource Identifiers (URI): Generic Syntax](https://www.ietf.org/rfc/rfc2396.txt) 这篇文章已经帮你更好滴理解URI。