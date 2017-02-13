package com.learning.domain;

import java.util.List;

public class RoleDomain {
    private Integer id;

    private String no;

    private String name;

    private List<PrivilegeDomain> privilegeDomainList;

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

    public List<PrivilegeDomain> getPrivilegeDomainList() {
        return privilegeDomainList;
    }

    public void setPrivilegeDomainList(List<PrivilegeDomain> privilegeDomainList) {
        this.privilegeDomainList = privilegeDomainList;
    }
}