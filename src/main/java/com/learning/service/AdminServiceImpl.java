package com.learning.service;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.dao.AdminDao;
import com.learning.dao.RoleDao;
import com.learning.domain.AdminDomain;
import com.learning.domain.RoleDomain;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 *
 * Created by lw on 16-12-19.
 */
public class AdminServiceImpl {
    public List<AdminDomain> adminList() {
        return AdminDao.selectAllAdmin();
    }

    public AdminDomain adminDetail(Integer id) {
        return AdminDao.selectByPrimaryKey(id);
    }

    @Transactional
    public Resp addAdmin(AdminDomain adminDomain) {
        AdminDomain adminDomain1 = AdminDao.selectByLoginName(adminDomain.getLoginName());
        if (adminDomain1 != null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该用户名已存在");
        } else {
            RoleDomain roleDomain = RoleDao.selectById(adminDomain.getRoleId());
            if (roleDomain != null) {
                adminDomain.setRoleName(roleDomain.getName());
            }
            AdminDao.add(adminDomain);
            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "添加成功");
        }
    }

    @Transactional
    public Resp update(AdminDomain adminDomain) {
        AdminDomain adminDomainUnit = AdminDao.selectByPrimaryKey(adminDomain.getId());
        if (adminDomainUnit == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该账号不存在");
        } else {
            RoleDomain roleDomain = RoleDao.selectById(adminDomain.getRoleId());
            if (roleDomain != null) {
                adminDomain.setRoleName(roleDomain.getName());
            }
            AdminDao.updateById(adminDomain);
            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "修改成功");
        }
    }

    @Transactional
    public Resp deleteById(Integer id) {
        AdminDomain adminDomain = AdminDao.selectByPrimaryKey(id);
        if (adminDomain == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该账号不存在");
        } else {
            AdminDao.deleteById(id);
            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "删除成功");
        }
    }
}
