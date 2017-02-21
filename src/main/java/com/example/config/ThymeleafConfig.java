package com.example.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateparser.markup.decoupled.IDecoupledTemplateLogicResolver;
import org.thymeleaf.templateparser.markup.decoupled.StandardDecoupledTemplateLogicResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;


@Configuration
@EnableWebMvc
@ComponentScan("com.example")
public class ThymeleafConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {

  private ApplicationContext applicationContext;

  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @Bean
  public ViewResolver viewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine( templateEngine());
    resolver.setCharacterEncoding("UTF-8");
    return resolver;
  }


  
  @Bean
  public TemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setEnableSpringELCompiler(true);
    engine.setTemplateResolver(webTemplateResolver());
    engine.addTemplateResolver(emailTemplateResolver());
    return engine;
  }
  
  
  private ITemplateResolver webTemplateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setApplicationContext(applicationContext);
    resolver.setPrefix("/templates/");
    resolver.setTemplateMode(TemplateMode.HTML);
    resolver.setUseDecoupledLogic(true);
    resolver.setOrder(1);
    return resolver;
  }
  
private SpringResourceTemplateResolver emailTemplateResolver() {
  SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
  templateResolver.setPrefix("/mail/");
  templateResolver.setSuffix(".html");
  templateResolver.setTemplateMode(TemplateMode.HTML);
  templateResolver.setOrder(2);
  return templateResolver;
}
  
//  private IDecoupledTemplateLogicResolver  dtemplateResolver() {
//	   StandardDecoupledTemplateLogicResolver decoupledresolver = 
//	            new StandardDecoupledTemplateLogicResolver();
////	    decoupledResolver.setPrefix("../viewlogic/");
//	  decoupledresolver.setPrefix("/templates/");
//	    return decoupledresolver;
//	  }
}
