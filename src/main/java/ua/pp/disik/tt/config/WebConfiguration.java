package ua.pp.disik.tt.config;

import freemarker.ext.jsp.TaglibFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by disik on 4/7/17.
 */
@EnableWebMvc
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/static/");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setPrefix("/");
        freeMarkerViewResolver.setSuffix(".ftl");
        freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");
        freeMarkerViewResolver.setCache(false);
        registry.viewResolver(freeMarkerViewResolver);
    }

    @Autowired
    public void d(FreeMarkerConfigurer freeMarkerConfigurer) {
        TaglibFactory.ClasspathMetaInfTldSource classpath = new TaglibFactory.ClasspathMetaInfTldSource(Pattern.compile(".*"));
        freeMarkerConfigurer.getTaglibFactory().setMetaInfTldSources(Arrays.asList(classpath));
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer(ServletContext servletContext) {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();

        freeMarkerConfigurer.setTemplateLoaderPath("/templates/");

//        freeMarkerConfigurer.setServletContext(servletContext);
//
//        TaglibFactory.ClasspathMetaInfTldSource classpath = new TaglibFactory.ClasspathMetaInfTldSource(Pattern.compile(".*"));
//
//        freeMarkerConfigurer.getTaglibFactory().setMetaInfTldSources(Arrays.asList(classpath));

        return freeMarkerConfigurer;
    }
}
