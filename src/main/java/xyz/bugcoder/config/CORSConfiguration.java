package xyz.bugcoder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Package: xyz.bugcoder.config
 * @author: Weiyj
 * @Description:  WebMvcConfigurerAdapter要使用这个类，
 *                重写addCorsMappings，添加头请求...，才能使用ajax，不然报cors错
 * @createTime 2019-09-16 10:36
 */
@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry){

        registry.addMapping("/**")
                .allowedOrigins("/*")
                .allowedHeaders("/*")
                .allowedMethods("/*");
    }

}
