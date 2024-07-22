package com.hoainamtd.configuration;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

// LocalResolver: Lớp cấu hình xử lý việc xác định ngôn ngữ từ header "Accept-Language".
// resolveLocale: Phương thức để xác định ngôn ngữ hiện tại từ yêu cầu HTTP.
// bundleMessageSource: Bean ResourceBundleMessageSource để quản lý các thông điệp quốc tế hóa trong ứng dụng.
@Configuration
public class LocalResolver extends AcceptHeaderLocaleResolver implements WebMvcConfigurer {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String languageHeader = request.getHeader("Accept-Language");
        return StringUtils
                .hasLength(languageHeader)
                ?
                    Locale.lookup(
                            Locale.LanguageRange.parse(languageHeader),
                            List.of(new Locale("en"), new Locale("fr")))
                :
                    Locale.getDefault();
    }

    @Bean
    public ResourceBundleMessageSource bundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }
}
