package com.github.caryyu.spring.embedded.redisserver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import java.io.IOException;

/**
 * <p>核心配置信息,一定要让Spring进行扫描到才可以生效</p>
 * <p>主要通过系统环境属性进行控制.</p>
 * @author cary
 * @date 2017/7/20
 */
@Configuration
public class RedisServerConfiguration implements DisposableBean {
    private Log log = LogFactory.getLog(this.getClass());
    private RedisServer redisServer;

    public RedisServerConfiguration(){
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

    /**
     * 通过环境属性(global.redis.port)进行控制
     * @return 端口号
     */
    public int getPort() {
        Object rawPort = System.getProperties().get("global.redis.port");
        int port = 6379;
        port = rawPort == null ? port : Integer.parseInt(rawPort.toString());
        return port;
    }

    /**
     * 通过环境属性(global.redis.embedded)进行控制
     * @return TRUE/FALSE
     */
    private boolean isEmbedded() {
        Object rawEmbedded = System.getProperties().get("global.redis.embedded");
        Boolean embedded = rawEmbedded == null ? false : Boolean.parseBoolean(rawEmbedded.toString());
        return embedded;
    }

    @Override
    public void destroy() throws Exception {
        if(redisServer != null) {
            redisServer.stop();
            redisServer = null;
        }
    }
}
