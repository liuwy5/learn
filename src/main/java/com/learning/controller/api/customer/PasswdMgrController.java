package com.learning.controller.api.customer;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.dao.PasswdDao;
import com.learning.domain.PasswdDomain;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by liuw on 17-1-20.
 */
@RequestMapping("/passwd")
@RestController
public class PasswdMgrController {
    @RequestMapping("/add")
    public Resp addPwd(PasswdDomain passwdDomain) {
        PasswdDomain passwdDomain1 = PasswdDao.selectByLoginName(passwdDomain.getLoginName());
        if (passwdDomain1 != null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该用户名已存在");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer interest : passwdDomain.getInterestList()) {
            stringBuilder.append(interest).append(";");
        }
        passwdDomain.setInterest(stringBuilder.toString());
        PasswdDao.addPasswd(passwdDomain);
        return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
    }
}
