# 参考连接
https://opencredo.com/spring-booting-hazelcast/

## 测试
hazelcast Map
100,000 简单字符串键值对(约3k大小)，无计算
    set 耗时110,218ms 峰值1203
    get 耗时64,552ms 峰值2021
GC可以回收内存


## instance 使用
- instance 创建
```
    ClientConfig config = new ClientConfig();
    ClientNetworkConfig networkConfig = config.getNetworkConfig();
    networkConfig.addAddress("10.5.6.34:5701");
    instance34 = HazelcastClient.newHazelcastClient(config);
```
- 使用前需要判断
判断当前实例是否可用，服务器宕机之后，实例不会自动重连，需要重新生成instance
`instance34.getLifecycleService().isRunning())`

也可以实现自定义事件监听器，可以监听到服务器的这些事件
```
STARTING,
STARTED,
SHUTTING_DOWN,
SHUTDOWN,
MERGING,
MERGED,
CLIENT_CONNECTED,
CLIENT_DISCONNECTED
```
`instance34.getLifecycleService().addLifecycleListener(new MyLifecycleListener());`
当收到SHUTDOWN事件之后，此instance就不可用了，需要重新生成