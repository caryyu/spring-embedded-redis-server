# spring-embedded-redis-server
A tiny redis embedded server fully integrated with Spring and Spring Boot, Only for testing or debugging. 

## version required
* Spring 3.1 +
* JDK 1.6 +

## local installation
> Note: this step is optional, Can be able to fetch the dependencies directly from Central.
```
git clone https://github.com/caryyu/spring-embedded-redis-server
mvn install
```

## maven dependencies
```
<dependency>
    <groupId>com.github.caryyu</groupId>
    <artifactId>spring-embedded-redis-server</artifactId>
    <version>1.0</version>
</dependency>
```

## spring configuration
We can pass the configuration as the following
> Note: we just only need to choose one of following three ones to pass.
### component scan
the package of `com.github.caryyu` is must.
```
<context:component-scan base-package="com.github.caryyu,xxxx" />
```
### modify `spring-context.xml` file
the xml filename based on your real situation.
```
<beans:bean class="com.github.caryyu.spring.embedded.redisserver.RedisServerConfiguration" id="redisServerConfiguration" />
```
### spring boot annotation
```
@Bean
public RedisServerConfiguration redisServerConfiguration(){
    return new RedisServerConfiguration();
}
```
## usage
* global.redis.port - this is the embedded port for server listening.
* global.redis.embedded - this is the switch to check whether or not it is ON/OFF.

### jvm environment variables
Actually , we have lots of ways to use this library, ofcourse i really recommend you according to [Spring official Environment Variables](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html),so i just give some usages below.
  
#### using for JavaSE
```
java -Dglobal.redis.embedded=true
```

#### using under Tomcat 
 we need to modify catalina.sh or catalina.bat or add new file that can name "setenv.sh" etc..
```
set JAVA_OPTS="-Dglobal.redis.embedded=true"
```

### application.properties
```
global.redis.port=6379
global.redis.embedded=true    
```
### application.tml 
```
global:
    redis:
        port: 6379
        embedded: true
```
## Contribution
All Contributions are welcomed.


