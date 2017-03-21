package com.lti.launch.config;

import com.lti.launch.service.LtiLaunchKeyServiceImpl;
import com.lti.launch.service.OauthTokenServiceImpl;
import edu.ksu.lti.launch.service.ConfigService;
import edu.ksu.lti.launch.service.LtiLaunchKeyService;
import edu.ksu.lti.launch.service.OauthTokenService;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.servlet.DispatcherType;

@Configuration
@ComponentScan({"com.lti.launch", "edu.ksu.lti.launch"})
public class AppConfig{

    private static final Logger LOG = Logger.getLogger(AppConfig.class);
    @Autowired
    private ConfigService configService;

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    @Bean
    public String canvasDomain() {
        return configService.getConfigValue("canvas_url");
    }

    @Bean
    public LtiLaunchKeyService getLtiLaunchKeyService() {
        LtiLaunchKeyServiceImpl ltiLaunchKeyService =  new LtiLaunchKeyServiceImpl();
        ltiLaunchKeyService.setSecretForKey(configService.getConfigValue("oauth_client_secret_key"), configService.getConfigValue("oauth_client_secret"));
        return ltiLaunchKeyService;
    }

    @Bean
    public OauthTokenService getOauthTokenService() {
        return new OauthTokenServiceImpl();
    }

    //configures the tomcat container used by spring to allow the tool to run as an iframe
    @Bean
    public FilterRegistrationBean myFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        HttpHeaderSecurityFilter headerSecurityFilter = new HttpHeaderSecurityFilter();
        headerSecurityFilter.setAntiClickJackingEnabled(true);
        headerSecurityFilter.setAntiClickJackingOption("ALLOW-FROM");
        headerSecurityFilter.setAntiClickJackingUri(configService.getConfigValue("canvas_url"));
        registration.setFilter(headerSecurityFilter);
        return registration;
    }

}
