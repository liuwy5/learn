package com.learning.service;

import com.learning.bean.Ticket;
import com.learning.common.enums.LoginRespEnum;
import com.learning.dao.AdminDao;
import com.learning.dao.PrivilegeDao;
import com.learning.domain.AdminDomain;
import com.learning.domain.PrivilegeDomain;
import com.learning.vo.LoginRetVo;
import com.learning.vo.LoginVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * AdminLoginServiceImpl
 * Created by lw on 16-12-16.
 */
@Service
public class AdminLoginServiceImpl {

    public LoginRetVo auth(LoginVo loginVo, HttpSession session) {
        AdminDomain adminDomain = AdminDao.selectByLoginName(loginVo.getLoginName());

        if (adminDomain == null) {
            return new LoginRetVo(LoginRespEnum.LOGIN_NO_VALID_LOGIN_NAME.getCode(), null);
        } else {
            if (adminDomain.getPassword().equals(loginVo.getLoginPwd())) {
                Ticket ticket = new Ticket();
                ticket.setLoginName(loginVo.getLoginName());
                ticket.setName(adminDomain.getName());
                session.setAttribute("ticket", ticket);

                // 查看第一个url
                List<PrivilegeDomain> privilegeDomainList = PrivilegeDao.selectPrivilegeByLoginName(loginVo.getLoginName());
                if (privilegeDomainList != null) {
                    return new LoginRetVo(LoginRespEnum.LOGIN_SUCCESS.getCode(), privilegeDomainList.get(0).getUrl());

                } else {
                    return new LoginRetVo(LoginRespEnum.LOGIN_NO_VALID_LOGIN_NAME.getCode(), null);
                }
            } else {
                return new LoginRetVo(LoginRespEnum.LOGIN_NO_VALID_PASSWORD.getCode(), null);
            }
        }
    }
}
