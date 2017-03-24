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
    @Value("${config.property.lti_launch_key}")
    private String ltiLaunchKey;
    @Value("${config.property.lti_launch_secret}")
    private String ltiLaunchSecret;

    public static void main(String [] args) {
        SpringApplication.run(AppInit.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AppConfig.class);
    }

    /**
     * Initializing static values here is unusual. We are doing it so we don't
     * have to depend on a database or other config source in this example
     * project. Usually you would provide a bean (probably in your AppConfig class)
     * that is able to look up values from a database or application properties.
     * @return ConfigService to look up config items.
     */
    @Bean
    public ConfigService getConfigService() {
        ConfigServiceImpl configService = new ConfigServiceImpl();
        configService.setConfigValue("canvas_url", canvasUrl);
        configService.setConfigValue("canvas_url_2", canvasUrl2);
        configService.setConfigValue("lti_launch_key", ltiLaunchKey);
        configService.setConfigValue("lti_launch_secret", ltiLaunchSecret);
        return configService;
    }
}
