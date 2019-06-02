package com.lr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.lr.controller")
public class WebMvcConfig {

    //声明页面的编码格式、类型
    private static final String CONTENTTYPE = "text/html; charset=UTF-8";

    //Thymeleaf配置
    @Bean
    public TemplateResolver templateResolver(){
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
        //设置模板位置
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        //去除缓存
        resolver.setCacheable(false);
        resolver.setCharacterEncoding("UTF-8");
        //不严格按照XHTML来检测
        resolver.setTemplateMode("LEGACYHTML5");

        return resolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver());
        return springTemplateEngine;
    }

    /**
     * 模板引擎解释器
     * @return
     */
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setContentType(CONTENTTYPE);
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }
}