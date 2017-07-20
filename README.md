# spring-embedded-redis-server
## MAVEN 依赖设置
### 本地安装
```
git clone https://github.com/caryyu/spring-embedded-redis-server
mvn install
```
### 依赖配置
注意：依赖包需要先进行以上本地方式安装方可找到。
```
<dependency>
    <groupId>com.github.caryyu</groupId>
    <artifactId>spring-embedded-redis-server</artifactId>
    <version>1.0</version>
</dependency>
```
## Spring 配置请设置扫描（com.github.caryyu）
```
<context:component-scan base-package="com.github.caryyu,xxxx" />
```
## 系统属性设置开启
使用VM属性进行设置开启内嵌服务，e.g.(-Dglobal.redis.embedded=true）。  
### global.redis.port
设置内嵌服务端口号
### global.redis.embedded
设置内嵌服务是否开启,true表示开启,false表示关闭。