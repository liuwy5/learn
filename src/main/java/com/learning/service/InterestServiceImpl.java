package com.learning.service;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.dao.InterestDao;
import com.learning.vo.InterestVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * Created by liuw on 17-1-9.
 */
@Service
public class InterestServiceImpl {
    private static Logger logger = LoggerFactory.getLogger(InterestServiceImpl.class);

    @Transactional
    public Resp addChat(InterestVo interestVo) {
        InterestDao.addInterest(interestVo.getChatName(), interestVo.getInterestType());
        return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
    }
}
