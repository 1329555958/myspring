# rabbitmq
`docker run -d --name rabbitmq -p 8080:15672 rabbitmq:3-management`

# zipkin
`docker run -d -p 9411:9411 -p 9410:9410 openzipkin/zipkin`

# stream
服务之间聚合只能使用source、sink、process；
http://cloud.spring.io/spring-cloud-static/spring-cloud.html#_connecting_multiple_application_instances
不同应用之间传递消息需要，source的output与sink的input相同；
同一个应用要使用聚合应用https://github.com/1329555958/spring-cloud-samples/tree/master/double

# 常见问题
* 绑定端口时映射失败
docker0: iptables: No chain/target/match by that name.
由于主机防火墙开启的原因，关闭防火墙之后要重启docker服务；

* 端口已经被绑定
 failed to create endpoint boring_visvesvaraya on network bridge: Error starting userland proxy:
主机端口被占用了，`netstat -nap|grep 端口号`，杀死对应的进程