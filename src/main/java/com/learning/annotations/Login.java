package com.learning.annotations;

import com.learning.common.enums.LoginActionEnum;
import com.learning.common.enums.LoginTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Created by liuw on 17-2-10.
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Login {
    LoginActionEnum action() default LoginActionEnum.NORMAL;

    LoginTypeEnum type() default LoginTypeEnum.ADMIN;
}
