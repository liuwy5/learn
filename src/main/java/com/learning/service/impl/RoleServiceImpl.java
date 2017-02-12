package com.learning.service.impl;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.domain.PrivilegeDomain;
import com.learning.domain.RoleDomain;
import com.learning.domain.RolePrivilegMappingDomain;
import com.learning.persistence.PrivilegeDomainMapper;
import com.learning.persistence.RoleDomainMapper;
import com.learning.persistence.RolePrivilegMappingDomainMapper;
import com.learning.service.IRoleService;
import com.learning.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by lw on 16-12-19.
 */
@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleDomainMapper roleDomainMapper;

    @Autowired
    private PrivilegeDomainMapper privilegeDomainMapper;

    @Autowired
    private RolePrivilegMappingDomainMapper rolePrivilegMappingDomainMapper;

    public List<RoleDomain> roleList() {
        return roleDomainMapper.selectAll();
    }

    public RoleVo roleDetail(Integer id) {
        RoleVo roleVo = new RoleVo();

        RoleDomain roleDomain = roleDomainMapper.selectByPrimaryKey(id);
        if (roleDomain != null) {
            roleVo.setId(roleDomain.getId());
            roleVo.setRoleName(roleDomain.getName());

            List<String> privilegeUuidList = new ArrayList<String>();
            List<RolePrivilegMappingDomain> rolePrivilegeMappingList = rolePrivilegMappingDomainMapper.selectByRoleUuid(roleDomain.getName());
            for (RolePrivilegMappingDomain rolePrivilegMappingDomain : rolePrivilegeMappingList) {
                privilegeUuidList.add(rolePrivilegMappingDomain.getPrivilegeId());
            }

            roleVo.setPrivilegeUuidList(privilegeUuidList);
        }
        return roleVo;
    }

    @Transactional
    public Resp addRole(RoleVo roleVo) {
        RoleDomain roleDomain1 = roleDomainMapper.selectByRoleName(roleVo.getRoleName());
        if (roleDomain1 != null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该角色名已存在");
        } else {
            RoleDomain roleDomain = new RoleDomain();
            roleDomain.setName(roleVo.getRoleName());
            roleDomainMapper.insert(roleDomain);

            // 添加角色权限映射
            addRolePrivilegeMapping(roleVo.getPrivilegeUuidList(), roleDomain.getName());

            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "添加成功");
        }
    }

    @Transactional
    public Resp update(RoleVo roleVo) {
        RoleDomain roleDomain1 = roleDomainMapper.selectByPrimaryKey(roleVo.getId());
        if (roleDomain1 == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该角色不存在");
        } else {
            RoleDomain roleDomain2 = roleDomainMapper.selectByRoleName(roleVo.getRoleName());
            if (roleDomain2 != null) {
                return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "新角色名称已存在");
            } else {
                RoleDomain roleDomain = new RoleDomain();
                roleDomain.setId(roleVo.getId());
                roleDomain.setName(roleVo.getRoleName());
                roleDomainMapper.updateByPrimaryKeySelective(roleDomain);

                rolePrivilegMappingDomainMapper.deleteByRoleUuid(roleVo.getUuid());

                // 添加角色权限映射
                addRolePrivilegeMapping(roleVo.getPrivilegeUuidList(), roleDomain1.getName());

                return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "修改成功");
            }

        }

    }

    @Transactional
    public Resp deleteById(Integer id) {
        RoleDomain roleDomain = roleDomainMapper.selectByPrimaryKey(id);
        if (roleDomain == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该角色不存在");
        } else {
            roleDomainMapper.deleteByPrimaryKey(id);
            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "删除成功");
        }
    }

    /**
     * 添加角色权限映射
     * @param privilegeUuidList privilegeUuidList
     */
    @Transactional
    private void addRolePrivilegeMapping(List<String> privilegeUuidList, String roleUuid) {
        for (String privilegeUuid : privilegeUuidList) {
            PrivilegeDomain privilegeDomain = privilegeDomainMapper.selectByUuid(privilegeUuid);
            if (privilegeDomain != null) {
                RolePrivilegMappingDomain rolePrivilegMappingDomain = new RolePrivilegMappingDomain();
//                rolePrivilegMappingDomain.setUuid(UuidUtil.getUuid());
//                rolePrivilegMappingDomain.setRoleUuid(roleUuid);
//                rolePrivilegMappingDomain.setPrivilegeUuid(privilegeUuid);
//                rolePrivilegMappingDomain.setPrivilegeNo(privilegeDomain.getPrivilegeNo());
//                rolePrivilegMappingDomain.setPrivilegeName(privilegeDomain.getPrivilegeName());
                rolePrivilegMappingDomainMapper.insert(rolePrivilegMappingDomain);
            }
        }
    }
}
