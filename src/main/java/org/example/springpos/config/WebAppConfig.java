package org.example.springpos.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Component
@ComponentScan(basePackages = "org.example.springpos")
@EnableWebMvc
@MultipartConfig
public class WebAppConfig {

}

