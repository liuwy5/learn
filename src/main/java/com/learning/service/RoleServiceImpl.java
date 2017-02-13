package com.learning.service;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.dao.RoleDao;
import com.learning.domain.RoleDomain;
import com.learning.vo.RoleVo;

import java.util.List;

/**
 *
 * Created by lw on 16-12-19.
 */
public class RoleServiceImpl {
    public List<RoleDomain> roleList() {
        return RoleDao.selectAllRole();
    }

    public RoleDomain roleDetail(Integer id) {
        return RoleDao.selectById(id);
    }

    public Resp addRole(RoleVo roleVo) {
        RoleDomain roleDomain1 = RoleDao.selectByName(roleVo.getName());
        if (roleDomain1 != null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该角色名已存在");
        } else {
            RoleDao.add(roleVo);
            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "添加成功");
        }
    }

    public Resp update(RoleVo roleVo) {
        RoleDomain roleDomain1 = RoleDao.selectById(roleVo.getId());
        if (roleDomain1 == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该角色不存在");
        } else {
            roleDomain1 = RoleDao.selectByNameExcept(roleVo.getName(), roleVo.getId());
            if (roleDomain1 != null) {
                return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该角色名已存在");
            } else {
                RoleDao.update(roleVo);
                return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "更新成功");
            }
        }
    }

    public Resp deleteById(Integer id) {
        RoleDomain roleDomain = RoleDao.selectById(id);
        if (roleDomain == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该角色不存在");
        } else {
            RoleDao.delete(id);
            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "删除成功");
        }
    }
}
