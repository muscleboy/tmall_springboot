package xyz.bugcoder.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * Create with IDEA.
 *
 * @Package: xyz.bugcoder.config
 * @Description: PageHelper分页设置
 * @Date: 2019/9/14 16:44
 * @Author: Wyj
 */
@Configuration
public class PageHelperConfig {

    @Bean
    PageHelper pageHelper(){

        PageHelper page = new PageHelper();
        Properties p = new Properties();
        // 设置分页属性
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        page.setProperties(p);

        return page;
    }

}
