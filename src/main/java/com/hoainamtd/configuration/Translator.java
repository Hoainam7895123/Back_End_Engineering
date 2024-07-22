package com.hoainamtd.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;


// Lớp Translator trong ứng dụng Spring Boot của bạn được thiết kế để hỗ trợ việc quốc tế hóa (i18n)
// bằng cách sử dụng ResourceBundleMessageSource để lấy các thông điệp từ các
// tệp .properties dựa trên ngôn ngữ hiện tại của người dùng.
@Component
public class Translator {

    private static ResourceBundleMessageSource messageSource;

    private Translator(@Autowired ResourceBundleMessageSource messageSource) {
        Translator.messageSource = messageSource;
    }

    public static String toLocale(String msgCode) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }
}
