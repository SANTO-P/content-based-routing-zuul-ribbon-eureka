package com.buybuy.zuulservice;

import com.buybuy.zuulservice.Ribbon.configuration.CustomRibbonConfiguration;
import com.buybuy.zuulservice.zuul.filter.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication(scanBasePackages={"com.netflix.client.config.IClientConfig"})
@RibbonClient(name = "domain-accounts", configuration = CustomRibbonConfiguration.class)
public class ZuulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServiceApplication.class, args);
	}

	@Bean
	public MyFilter myFilter() {
		return new MyFilter();
	}

}
