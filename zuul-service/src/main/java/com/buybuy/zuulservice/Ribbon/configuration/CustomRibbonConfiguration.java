package com.buybuy.zuulservice.Ribbon.configuration;

import com.buybuy.zuulservice.Ribbon.rule.MyRule;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


public class CustomRibbonConfiguration {

    @Autowired
    IClientConfig config;

    @Bean
    public IRule ribbonRule(IClientConfig config) {
        return  new MyRule();
    }
}
