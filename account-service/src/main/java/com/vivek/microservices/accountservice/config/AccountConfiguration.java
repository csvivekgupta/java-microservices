package com.vivek.microservices.accountservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import brave.sampler.Sampler;

@Configuration
@ComponentScan("com.vivek.microservices.accountservice")
public class AccountConfiguration {
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
}
