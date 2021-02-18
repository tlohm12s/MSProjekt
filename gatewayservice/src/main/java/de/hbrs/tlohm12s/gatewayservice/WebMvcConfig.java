package de.hbrs.tlohm12s.gatewayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//Initialize the Interceptor
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //Accept any path after service
    private static final String PATHS = "/service/**";

    @Autowired
    private CustomInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor).addPathPatterns(PATHS);
    }

}
