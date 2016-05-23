# jsonrpc 使用介绍
[https://github.com/briandilley/jsonrpc4j](https://github.com/briandilley/jsonrpc4j)

## 1.1 之前导出接口
```
    @Bean
    public AutoJsonRpcServiceExporter autoJsonRpcServiceImplExporter() {
        return new AutoJsonRpcServiceExporter();
    }
```
会把所有使用JsonRpcService注解的接口导出;
实现类无需使用@AutoJsonRpcServiceImpl注解

## 1.4 导出接口
```
    @Bean
    public AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter(){
        return new AutoJsonRpcServiceImplExporter();
    }
    
```
因为如果提供rpc接口的工程，同时又需要调用其他工程的rpc接口，就会把其他工程的接口也导出出去，为了解决这个问题，所以要明确指定导出
使用@AutoJsonRpcServiceImpl注解的bean上，并且实现使用@JsonRpcService注解的接口
导出bean时有问题，name must not be null，未找到错误原因，并且此种方法使用起来也不方便；

## 调用说明
mq工程下面有调用示例

http调用
POST http://localhost:8080/rpc/user
{"jsonrpc": "2.0","id":"11","method":"findUserByIdAndName","params":["1","qwe"]}