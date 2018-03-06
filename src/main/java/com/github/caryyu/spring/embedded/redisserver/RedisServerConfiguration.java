package com.github.caryyu.spring.embedded.redisserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import redis.embedded.RedisServer;

import java.io.IOException;

/**
 * 核心配置信息,一定要让Spring进行扫描到才可以生效,
 * 主要通过系统环境属性进行控制.
 *
 * @author cary
 * @since Spring 3.1
 */
@Component
public class RedisServerConfiguration implements DisposableBean, EnvironmentAware, InitializingBean {
    private Log log = LogFactory.getLog(this.getClass());
    private RedisServer redisServer;
    private Environment environment;

    /**
     * 通过环境属性(global.redis.port)进行控制
     * @return 端口号
     */
    public int getPort() {
        return environment.getProperty("global.redis.port",Integer.class,6379);
    }

    /**
     * 通过环境属性(global.redis.embedded)进行控制
     * @return TRUE/FALSE
     */
    private boolean isEmbedded() {
        return environment.getProperty("global.redis.embedded",Boolean.class,false);
    }

    @Override
    public void destroy() throws Exception {
        if(redisServer != null) {
            redisServer.stop();
            redisServer = null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 如果Redis嵌入式服务器配置是关闭的则直接跳出
        if (!isEmbedded()) {
            return;
        }

        try {
            int port = getPort();
            redisServer = new RedisServer(port);
            redisServer.start();

            if(log.isInfoEnabled()) {
                log.info("开启本地嵌入式Redis服务器成功,端口:" + port);
            }
        } catch (IOException e) {
            throw new FatalBeanException("开启嵌入的Redis服务器失败.", e);
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
