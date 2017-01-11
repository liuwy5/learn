package com.learning.service;

import com.learning.bean.Ticket;
import com.learning.common.enums.LoginRespEnum;
import com.learning.dao.AdminDao;
import com.learning.domain.AdminDomain;
import com.learning.vo.LoginVo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * AdminLoginServiceImpl
 * Created by lw on 16-12-16.
 */
@Service
public class AdminLoginServiceImpl {

    public Integer auth(LoginVo loginVo, HttpSession session) {
        AdminDomain adminDomain = AdminDao.selectByLoginName(loginVo.getLoginName());

        if (adminDomain == null) {
            return LoginRespEnum.LOGIN_NO_VALID_LOGIN_NAME.getCode();
        } else {
            if (adminDomain.getPassword().equals(loginVo.getLoginPwd())) {
                Ticket ticket = new Ticket();
                ticket.setLoginName(loginVo.getLoginName());
                ticket.setName(loginVo.getLoginName());
                session.setAttribute("ticket", ticket);

                return LoginRespEnum.LOGIN_SUCCESS.getCode();
            } else {
                return LoginRespEnum.LOGIN_NO_VALID_PASSWORD.getCode();
            }
        }
    }
}
