# Session控制器 客户端

> 客户端控制Session（会话）的开始与结束。

实现一个Session控制器(实现client端)。这里的Session 可以想象为一些有时间长度的会话请求。会话起停需要通过向服务器提供的http 接口发送请求来控制。
具体要求如下：
*	根据接口描述和协议类型发送 session start/stop请求给服务端
*	可支持同时(向同一个server)并发发送多个请求
*	Stop请求需要根据session 时长来发送
*	创建日志来记录session 状态 (发送时间,发送url,和body,结果,连接信息)

额外实现：
* 支持异步
* 支持动态调整并发参数

TODO：
* 动态调整session时长
* 更多的场景模拟
* UML图
* 优雅停止


## 使用指南


### 环境准备

* JDK 1.8

### 运行步骤

1. 导入Maven项目
2. 运行sessionserver下的SessionserverApplication类
3. 运行sessionclient下的Main类

## 配置方法

application.properties可配置属性
* durTime 会话持续时间
* threadNum 启动的线程数
* async 是否采用异步 1:是 0:否

## 异步方案
Apache的HttpAsyncClient包