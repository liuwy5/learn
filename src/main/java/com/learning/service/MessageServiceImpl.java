package com.learning.service;

import com.learning.dao.MessageDao;
import com.learning.domain.MessageDomain;

import java.util.List;

/**
 *
 * Created by liuw on 17-1-13.
 */
public class MessageServiceImpl {

    public List<MessageDomain> selectPrivateMessage(String sender, String receiver) {
        return MessageDao.selectPrivateMessage(sender, receiver);
    }

    public List<MessageDomain> selectGroupMessage(Integer interestId, String loginName) {
        return MessageDao.selectGroupMessage(interestId, loginName);
    }
}
