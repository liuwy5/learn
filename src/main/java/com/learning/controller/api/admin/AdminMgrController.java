package com.learning.controller.api.admin;

import com.learning.bean.Resp;
import com.learning.domain.AdminDomain;
import com.learning.service.AdminServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by liuw on 17-2-10.
 */
@RestController
@RequestMapping("/admin")
public class AdminMgrController {
    private AdminServiceImpl adminService = new AdminServiceImpl();

    @RequestMapping("/detail")
    public AdminDomain detail(Integer id) {
        return adminService.adminDetail(id);
    }

    @RequestMapping("/add")
    public Resp addAdmin(AdminDomain adminDomain) {
        return adminService.addAdmin(adminDomain);
    }

    @RequestMapping("/update")
    public Resp update(AdminDomain adminDomain) {
        return adminService.update(adminDomain);
    }

    @RequestMapping("delete")
    public Resp delete(Integer id) {
        return adminService.deleteById(id);
    }
}
