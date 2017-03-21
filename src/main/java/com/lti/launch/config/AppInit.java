package com.lti.launch.config;

import com.lti.launch.service.ConfigServiceImpl;
import edu.ksu.lti.launch.service.ConfigService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppInit extends SpringBootServletInitializer {
    // these can come from a datasource containing the configuration details
    @Value("${config.property.canvasUrl}")
    private String canvasUrl;
    @Value("${config.property.canvasUrl2}")
    private String canvasUrl2;
    @Value("${config.property.oAuthClientId}")
    private String oAuthClientId;
    @Value("${config.property.oAuthClientSecret}")
    private String oAuthClientSecret;
    @Value("${config.property.oAuthClientSecretKey}")
    private String oAuthClientSecretKey;

    public static void main(String [] args) {
        SpringApplication.run(AppInit.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppConfig.class);
    }

    @Bean
    public ConfigService getConfigService() {
        ConfigServiceImpl configService = new ConfigServiceImpl();
        configService.setConfigValue("canvas_url", canvasUrl);
        configService.setConfigValue("canvas_url_2", canvasUrl2);
        configService.setConfigValue("oauth_client_id", oAuthClientId);
        configService.setConfigValue("oauth_client_secret", oAuthClientSecret);
        configService.setConfigValue("oauth_client_secret_key", oAuthClientSecretKey);
        return configService;
    }
}
