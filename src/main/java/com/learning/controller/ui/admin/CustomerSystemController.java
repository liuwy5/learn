package com.learning.controller.ui.admin;

import com.learning.annotations.Login;
import com.learning.dao.AdminDao;
import com.learning.dao.RoleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String customerList(Model model) {
        model.addAttribute("adminList", AdminDao.selectAllAdmin());
        model.addAttribute("roleList", RoleDao.selectAllRole());
        return "admin/systemCustomer";
    }
}
