package com.learning.persistence;

import com.learning.domain.RolePrivilegeMappingDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RolePrivilegMappingDomainMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByRoleUuid(@Param("roleUuid") String roleUuid);

    int insert(RolePrivilegeMappingDomain record);

    int insertSelective(RolePrivilegeMappingDomain record);

    RolePrivilegeMappingDomain selectByPrimaryKey(Integer id);

    List<RolePrivilegeMappingDomain> selectByRoleUuid(@Param("roleUuid") String roleUuid);

    int updateByPrimaryKeySelective(RolePrivilegeMappingDomain record);

    int updateByPrimaryKey(RolePrivilegeMappingDomain record);
}