# 参考连接
https://opencredo.com/spring-booting-hazelcast/

## 测试
hazelcast Map
100,000 简单字符串键值对(约3k大小)，无计算
    set 耗时110,218ms 峰值1203
    get 耗时64,552ms 峰值2021
GC可以回收内存
