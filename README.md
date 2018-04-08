# TBS
## 一、开篇
最近坦白说也是异常的火，作为开发者的我也去赶紧跑过去蹭了一下热度，写了个安卓的，加载了头像，点击后直接跳转到QQ资料卡页面，并且优化了已有的解密的算法(已有的大多数情况不能完全解密)，目前还没发现解密出现问题的。
看看图
![image.png](https://upload-images.jianshu.io/upload_images/3110248-7af00a967121f8ef.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


## 二、分析
首先，要拿到数据的接口，得先拿到对应的token，而这个token就是使用skey进行一系列计算得出的，这个skey就是登录QQ空间后从返回的cookie中可以获取的。计算出token后，就可以放到这个接口，以获取好友的数据为例
```
https://ti.qq.com/cgi-node/honest-say/receive/friends?_client_version=0.0.7&token=
```
这样就可以拿到返回后的Json数据，然后就开始操作了
```
{
"code": 0,
"data": {
"confesses": [],
"maxUnread": 0,
"cookie": "CIsB",
"finish": 0
}
}
```
看看，这段数据就是返回后的Json数据，confesses就是返回后的数据列表，注意这里面的cookie，也就是进行分页加载需要传递的参数，如果没有数据了，这个finish就会变为1，cookie直接加到后面
```
https://ti.qq.com/cgi-node/honest-say/receive/friends?_client_version=0.0.7&token=计算的token&cookie=CIsB
```
这样就能加载完整的数据了，然后关键是解密，这里的话已经有公开的，但是解密不完整，这里我花了一些时间，这个解密的规则是先2位加密的字符对应一位数，然后2位数字就是1位对应1位，3个数字一组，下一组就又是2位对应1位，也就是对应4个加密后的字符，这样的规则，但是问题就出在了尾数，基本现在的这些就是尾数会解密出错，尾数又是使用的不同解密方式，经过多次测试，还是发现了规律，如图，这是尾数刚好对应3位一组的第前2位的。
![image.png](https://upload-images.jianshu.io/upload_images/3110248-e794c0bb9caf5057.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

这就是经过多次的对比，总结出的，但这里又有一个问题，就是不知道qq的位数和尾数的加密有没有关联，目前解密已经没发现问题了，这些就不多说了，体验地址
[下载](https://github.com/Xchuanshuo/TBS/blob/master/app/release/app-release.apk),给出[github](https://github.com/Xchuanshuo/TBS)地址，喜欢的欢迎给个star。

