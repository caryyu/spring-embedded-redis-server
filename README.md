# spring-embedded-redis-server
依赖 Spring 容器的 Redis 内嵌小型服务器，仅做调试或测试时使用。a Redis embedded server based on Spring, Only for testing or debugging.

## 版本需求 / Version Required
* Spring 3.1 +
* JDK 1.6 +

## 本地安装 / Local Installation
```
git clone https://github.com/caryyu/spring-embedded-redis-server
mvn install
```
## 依赖配置 / Maven Dependencies
注意：依赖包需要先进行以上本地方式安装方可找到。
```
<dependency>
    <groupId>com.github.caryyu</groupId>
    <artifactId>spring-embedded-redis-server</artifactId>
    <version>1.0</version>
</dependency>

<dependency>
    <groupId>com.github.kstyrc</groupId>
    <artifactId>embedded-redis</artifactId>
    <version>0.6</version>
    <scope>runtime</scope>
</dependency>
```

## Spring 核心配置 / Spring Configuration
### 方式一
直接配置此项目的包名前缀进行自动扫描。
```
<context:component-scan base-package="com.github.caryyu,xxxx" />
```
### 方式二
配置 XML 文件。
```
<beans:bean class="com.github.caryyu.spring.embedded.redisserver.RedisServerConfiguration" id="redisServerConfiguration" />
```
### 方式二
Spring Boot 注解配置
```
@Bean
public RedisServerConfiguration redisServerConfiguration(){
    return new RedisServerConfiguration();
}
```
## 使用方式 / Usage
* global.redis.port - 设置内嵌服务端口号
* global.redis.embedded - 设置内嵌服务是否开启,true表示开启,false表示关闭。
### JVM 环境变量 &
使用VM属性进行设置开启内嵌服务，e.g.(-Dglobal.redis.embedded=true）。
### 设置 application.properties 文件
```
global.redis.port=6379
global.redis.embedded=true    
```
### 设置 application.yml 文件
```
global:
    redis:
        port: 6379
        embedded: true
```
## 贡献 / Contribution
欢迎提交 PR 来进行改进。 All PRs are welcomed.


