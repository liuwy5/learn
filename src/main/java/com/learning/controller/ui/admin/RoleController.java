package com.learning.controller.ui.admin;

import com.learning.annotations.Login;
import com.learning.dao.PrivilegeDao;
import com.learning.dao.RoleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by lw on 16-12-17.
 */
@Controller
@RequestMapping("admin/role")
public class RoleController {
    @RequestMapping("")
    @Login
    public String roleList(Model model) {
        model.addAttribute("roleList", RoleDao.selectAllRole());
        model.addAttribute("privilegeList", PrivilegeDao.selectAllPrivilege());
        return "admin/role";
    }
}
