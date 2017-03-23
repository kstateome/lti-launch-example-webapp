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
        ltiLaunchKeyService.setSecretForKey(configService.getConfigValue("lti_launch_key"), configService.getConfigValue("lti_launch_secret"));
        return ltiLaunchKeyService;
    }

    @Bean
    public OauthTokenService getOauthTokenService() {
        return new OauthTokenServiceImpl();
    }

    //Configures the tomcat container used by spring to allow the tool to run as an iframe.
    //We already do this in the LtiLaunchSecurityConfig class over in lti-launch but
    //some combination of Spring Boot and embedded tomcat was adding a second
    //"X-Frame-Options: DENY" header and causing browsers to refuse to display our content
    //in a frame. This prevents the second DENY header from being added to requests.
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
