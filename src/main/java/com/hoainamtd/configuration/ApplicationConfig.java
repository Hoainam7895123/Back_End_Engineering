package com.hoainamtd.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class ApplicationConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        // Lớp này cho phép đăng ký các cấu hình CORS dựa trên URL.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Lớp này chứa các thiết lập cho CORS, chẳng hạn như nguồn gốc (origin), tiêu đề (header),
        // và phương thức (method) được phép
        CorsConfiguration config = new CorsConfiguration();

        // Cho phép gửi thông tin xác thực (cookies, HTTP authentication) cùng với các yêu cầu.
        config.setAllowCredentials(true);

        // Chỉ định nguồn gốc (origin) được phép, chỉ có các yêu cầu từ http://localhost:5173 được phép
        config.addAllowedOrigin("http://localhost:5173");

        // Cho phép tất cả các tiêu đề
        config.addAllowedHeader("*");

        // Cho phét tất cả các phương thức HTTP (GET, POST, ...)
        config.addAllowedMethod("*");

        // Đăng ký cấu hình CORS cho tất cả các đường dẫn (/**)
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
