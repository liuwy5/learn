package com.learning.controller.ui.admin;

import com.learning.annotations.Login;
import com.learning.service.PasswdServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册用户
 * Created by lw on 16-12-17.
 */
@Controller
@RequestMapping("admin/register/customer")
public class CustomerRegisterController {
    private PasswdServiceImpl passwdService = new PasswdServiceImpl();

    @RequestMapping("")
    @Login
    public String registerCustomerList(Model model) {
        model.addAttribute("passwdList", passwdService.passwdList());
        return "admin/registerCustomer";
    }
}
