package com.learning.controller.ui.admin;

import com.learning.annotations.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by lw on 16-12-17.
 */
@Controller
@RequestMapping("admin/system/customer")
public class CustomerSystemController {
    @RequestMapping("")
    @Login
    public String customerList() {
        return "admin/systemCustomer";
    }
}
