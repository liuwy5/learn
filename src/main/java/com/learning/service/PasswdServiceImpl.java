package com.learning.service;

import com.learning.common.enums.InterestTypeEnum;
import com.learning.dao.PasswdDao;
import com.learning.domain.PasswdDomain;
import com.learning.vo.FriendVo;
import com.learning.vo.InterestFriendVo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by lw on 16-12-19.
 */
public class PasswdServiceImpl {
    public List<PasswdDomain> passwdList() {
        return PasswdDao.selectAllPasswd();
    }

    /**
     * 首页非好友 依兴趣而成的组
     */
    public List<InterestFriendVo> getInterestFriend(String selfLoginName) {
        List<InterestFriendVo> interestFriendVoList = new ArrayList<InterestFriendVo>(InterestTypeEnum.values().length);

        for (InterestTypeEnum interestTypeEnum : InterestTypeEnum.values()) {
            InterestFriendVo interestFriendVo = new InterestFriendVo();
            interestFriendVo.setInterestCode(interestTypeEnum.getCode());
            interestFriendVo.setInterestMenu(interestTypeEnum.getMean());


            String interestCodeString = interestTypeEnum.getCode().toString();
            List<PasswdDomain> passwdDomainList;
            if (selfLoginName == null) {
                passwdDomainList = PasswdDao.selectByInterest(interestCodeString);
            } else {
                passwdDomainList = PasswdDao.selectByInterestExceptSelf(interestCodeString, selfLoginName);
            }
            List<FriendVo> friendVoList = new ArrayList<FriendVo>(passwdDomainList.size());
            for (PasswdDomain passwdDomain : passwdDomainList) {
                FriendVo friendVo = new FriendVo();
                friendVo.setLoginName(passwdDomain.getLoginName());
                friendVoList.add(friendVo);
            }
            interestFriendVo.setInterestFriendList(friendVoList);

            interestFriendVoList.add(interestFriendVo);
        }

        return interestFriendVoList;
    }
}
