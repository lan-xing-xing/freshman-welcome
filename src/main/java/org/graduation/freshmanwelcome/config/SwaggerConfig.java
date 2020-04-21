package org.graduation.freshmanwelcome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    //配置swagger的学生Docket的bean实例
    @Bean
    public Docket studentDocket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");

        //通过nvironment.acceptsProfiles判断是否处在自己设置的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("student")
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("org.graduation.freshmanwelcome.controller"))
                .build();
    }

    //配置swagger的后台Docket的bean实例
    @Bean
    public Docket adminDocket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");

        //通过nvironment.acceptsProfiles判断是否处在自己设置的环境当中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("org.graduation.freshmanwelcome.handler"))
                .build();
    }

    //配置swagger信息
    private ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("Alisa", "", "2868714995@qq.com");
        return new ApiInfo(
                "graduationProject API Documentation",
                "graduationProject Api Documentation",
                "v1.0",
                "http://120.79.131.49:8089",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
