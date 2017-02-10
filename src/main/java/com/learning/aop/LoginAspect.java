package com.learning.aop;

import com.learning.annotations.Login;
import com.learning.common.enums.LoginActionEnum;
import com.learning.common.enums.LoginTypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 *
 * Created by liuw on 17-2-10.
 */
@Component
@Aspect
public class LoginAspect {
    @Around("@annotation(com.learning.annotations.Login)")
    public Object auth(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        Login login = method.getAnnotation(Login.class);
        if (login != null && login.action() == LoginActionEnum.NORMAL && !isLogin()) {
            if (login.type() == LoginTypeEnum.ADMIN) {
                return "redirect:/admin/login";
            } else {
                return "redirect:/";
            }
        } else {
            return joinPoint.proceed();
        }
    }

    private boolean isLogin() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession httpSession = attrs.getRequest().getSession();
        Object ticket = httpSession.getAttribute("ticket");
        return ticket != null;
    }
}
