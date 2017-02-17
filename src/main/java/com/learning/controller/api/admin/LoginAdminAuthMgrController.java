package com.learning.controller.api.admin;

import com.learning.service.AdminLoginServiceImpl;
import com.learning.vo.LoginVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpSession;

/**
 *
 * Created by lw on 16-12-16.
 */
@RestController
@RequestMapping("/admin/login")
public class LoginAdminAuthMgrController {
    private AdminLoginServiceImpl adminLoginService = new AdminLoginServiceImpl();

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public Object auth(LoginVo loginVo, HttpSession session) {
        return adminLoginService.auth(loginVo, session);
    }
}
