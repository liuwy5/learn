package com.learning.controller.ui.admin;

import com.learning.annotations.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册用户
 * Created by lw on 16-12-17.
 */
@Controller
@RequestMapping("admin/register/customer")
public class CustomerRegisterController {
    @RequestMapping("")
    @Login
    public String registerCustomerList() {
        return "admin/registerCustomer";
    }
}
