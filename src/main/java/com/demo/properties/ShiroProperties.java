package com.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: shiro-demo
 * @description:
 * @author: chentao
 * @create: 2018-10-22 17:24
 **/
@ConfigurationProperties(prefix = "myshiro")
public class ShiroProperties  {
    private List<String> ignoreUrl = new ArrayList<>();

    public List<String> getIgnoreUrl() {
        return ignoreUrl;
    }

    public void setIgnoreUrl(List<String> ignoreUrl) {
        this.ignoreUrl = ignoreUrl;
    }
}
