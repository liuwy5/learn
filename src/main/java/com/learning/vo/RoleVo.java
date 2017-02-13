package com.learning.vo;

import java.util.List;

/**
 *
 * Created by lw on 16-12-19.
 */
public class RoleVo {
    private Integer id;

    private String no;

    private String name;

    private List<Integer> privilegeIdList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String roleName) {
        this.name = roleName == null ? null : roleName.trim();
    }

    public List<Integer> getPrivilegeIdList() {
        return privilegeIdList;
    }

    public void setPrivilegeIdList(List<Integer> privilegeIdList) {
        this.privilegeIdList = privilegeIdList;
    }
}
