package com.buybuy.zuulservice.Ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MyRule extends AbstractLoadBalancerRule {

    private static Logger log = LoggerFactory.getLogger(MyRule.class);
    private ILoadBalancer lb;

    public MyRule(){}

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), (String) key);
    }

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.lb;
    }

    public Server choose(ILoadBalancer lb, String key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        } else {
            Server server = null;
            int count = 0;

            while(true) {
                if (server == null && count++ < 10) {
                    List<Server> reachableServers = lb.getReachableServers();
                    List<Server> allServers = lb.getAllServers();
                    int upCount = reachableServers.size();
                    int serverCount = allServers.size();
                    int requiredPort = 0;
                    if(key.equals("account-history")) {
                        requiredPort = 8091;
                    }else{
                        requiredPort = 8090;
                    }
                    if (upCount != 0 && serverCount != 0) {
                        for (Server server1: reachableServers) {
                            if(server1.getPort() == requiredPort) {
                                server = server1;
                                break;
                            }
                        }

                        if (server == null) {
                            Thread.yield();
                        } else {
                            if (server.isAlive() && server.isReadyToServe()) {
                                return server;
                            }

                            server = null;
                        }
                        continue;
                    }

                    log.warn("No up servers available from load balancer: " + lb);
                    return null;
                }

                if (count >= 10) {
                    log.warn("No available alive servers after 10 tries from load balancer: " + lb);
                }

                return server;
            }
        }
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
