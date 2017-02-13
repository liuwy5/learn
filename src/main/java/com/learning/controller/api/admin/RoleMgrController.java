package com.learning.controller.api.admin;

import com.learning.bean.Resp;
import com.learning.domain.RoleDomain;
import com.learning.service.RoleServiceImpl;
import com.learning.vo.RoleVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by liuw on 17-2-12.
 */
@RequestMapping("/role")
@RestController
public class RoleMgrController {
    private RoleServiceImpl roleService = new RoleServiceImpl();

    @RequestMapping("/detail")
    public RoleDomain detail(Integer id) {
        return roleService.roleDetail(id);
    }

    @RequestMapping("/add")
    public Resp add(RoleVo roleVo) {
        return roleService.addRole(roleVo);
    }

    @RequestMapping("/update")
    public Resp update(RoleVo roleVo) {
        return roleService.update(roleVo);
    }

    @RequestMapping("/delete")
    public Resp delete(Integer id) {
        return roleService.deleteById(id);
    }
}
