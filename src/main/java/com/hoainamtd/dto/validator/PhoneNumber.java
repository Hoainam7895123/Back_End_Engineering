package com.hoainamtd.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class) // ràng buộc gọi qua PhoneValidator.class
@Target( { ElementType.METHOD, ElementType.FIELD }) // áp dụng với method và field
@Retention(RetentionPolicy.RUNTIME) // chạy trên môi trường runtime
public @interface PhoneNumber {
    // nếu PhoneValidator return false sẽ bắn ra message này.
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
