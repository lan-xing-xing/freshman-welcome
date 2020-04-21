package org.graduation.freshmanwelcome.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")//允许所有的访问请求（访问路基）
                .allowedMethods("*")//允许所有的请求方法访问该跨域资源服务器
                .allowedOrigins("*")//允许所有的请求域名访问我们的跨域资源
                .allowedHeaders("*");//允许所有的请求handler访问
        super.addCorsMappings(registry);
    }
}
