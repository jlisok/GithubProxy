package com.jlisok.githubproxy.async;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

@Value
@ConfigurationProperties(prefix = "async")
public class AsyncProperties {
    int maxThreadPoolSize;
    int timeout;
    TimeUnit unit;
}
