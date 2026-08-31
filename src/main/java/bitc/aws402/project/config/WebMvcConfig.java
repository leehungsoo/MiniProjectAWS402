package bitc.aws402.project.config;

import bitc.aws402.project.interceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCheck())
        .addPathPatterns("/admin/**")
        .excludePathPatterns("/admin")
        .excludePathPatterns("/admin/login")
        .excludePathPatterns("/admin/logout");
  }
}
