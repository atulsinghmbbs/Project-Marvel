package com.haarmk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("secure.properties")
@PropertySource("application.properties")
@PropertySource("application.yaml")
public class PropertiesConfig {
	

}
