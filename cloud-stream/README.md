# rabbitmq
`docker run -d --name rabbitmq -p 8080:15672 rabbitmq:3-management`

# zipkin
`docker run -d -p 9411:9411 -p 9410:9410 openzipkin/zipkin`
`docker run --name zipkin-cassandra --env STORAGE_TYPE=cassandra --env STORAGE_PORT_9042_TCP_ADDR=10.5.16.14 -d -p 9511:9411 -p 9510:9410 openzipkin/zipkin`
`docker run --name cassandra -p 7001:7001 -p 7199:7199 -p 9042:9042 -p 7000:7000 -p 9160:9160 -v /var/cassandra/data:/var/lib/cassandra -d cassandra:latest`

# stream
服务之间聚合只能使用source、sink、process；
http://cloud.spring.io/spring-cloud-static/spring-cloud.html#_connecting_multiple_application_instances
不同应用之间传递消息需要，source的output与sink的input相同；
同一个应用要使用聚合应用https://github.com/1329555958/spring-cloud-samples/tree/master/double

# Zipkin stream
zipkin数据收集可能会成为瓶颈，所以通过spring cloud stream的方式收集，将追踪数据通过stream发送到Rabbitmq；另起一台服务器去消费消息并把这些数据存储起来；
- 应用内存储Zipkin
添加依赖`compile 'org.springframework.cloud:spring-cloud-sleuth-zipkin'`就可以了
配置Zipkin地址`spring.zipkin.baseUrl= http://10.5.16.14:9411/`
- 将Zipkin数据发送到stream
将上面的依赖去掉，添加依赖`compile 'org.springframework.cloud:spring-cloud-sleuth-stream'`
配置Rabbitmq地址就行了
```
spring.rabbitmq.host=10.5.16.14
spring.rabbitmq.port=5672
```

# 常见问题
* 绑定端口时映射失败
docker0: iptables: No chain/target/match by that name.
由于主机防火墙开启的原因，关闭防火墙之后要重启docker服务；

* 端口已经被绑定
 failed to create endpoint boring_visvesvaraya on network bridge: Error starting userland proxy:
主机端口被占用了，`netstat -nap|grep 端口号`，杀死对应的进程