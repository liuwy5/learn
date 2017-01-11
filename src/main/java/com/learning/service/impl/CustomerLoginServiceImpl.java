package com.learning.service.impl;

import com.learning.bean.Resp;
import com.learning.bean.Ticket;
import com.learning.common.enums.LoginRespEnum;
import com.learning.common.enums.RespStatusEnum;
import com.learning.common.util.TicketUtil;
import com.learning.domain.PasswdDomain;
import com.learning.persistence.PasswdDomainMapper;
import com.learning.service.ICustomerLoginService;
import com.learning.vo.LoginVo;
import com.learning.vo.ModifyPasswdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;

/**
 *
 * Created by lw on 16-12-21.
 */
@Service
public class CustomerLoginServiceImpl implements ICustomerLoginService {
    @Autowired
    private PasswdDomainMapper passwdDomainMapper;

    public Integer auth(LoginVo loginVo, HttpSession session) {
        PasswdDomain passwdDomain = passwdDomainMapper.selectByLoginName(loginVo.getLoginName());

        if (passwdDomain == null) {
            return LoginRespEnum.LOGIN_NO_VALID_LOGIN_NAME.getCode();
        } else {
            if (passwdDomain.getPassword().equals(loginVo.getLoginPwd())) {
                Ticket ticket = new Ticket();
                ticket.setLoginName(loginVo.getLoginName());
                ticket.setName(passwdDomain.getName());
                session.setAttribute("ticket", ticket);

                return LoginRespEnum.LOGIN_SUCCESS.getCode();
            } else {
                return LoginRespEnum.LOGIN_NO_VALID_PASSWORD.getCode();
            }
        }
    }

    @Transactional
    public Resp changePwd(ModifyPasswdVo modifyPasswdVo, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);
        if (loginName == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "未登录");
        } else {
            PasswdDomain passwdDomain = passwdDomainMapper.selectByLoginName(loginName);
            if (modifyPasswdVo.getOldLoginPsw().equals(passwdDomain.getPassword())) {
                passwdDomain.setPassword(modifyPasswdVo.getLoginPsw());
                passwdDomainMapper.updateByPrimaryKey(passwdDomain);
                session.setAttribute("ticket", null);
                return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "修改密码成功");
            } else {
                return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "密码不正确");
            }

        }
    }
}
