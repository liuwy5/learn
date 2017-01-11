package com.learning.service.impl;

import com.learning.domain.FriendDomain;
import com.learning.persistence.FriendDomainMapper;
import com.learning.service.IFriendService;
import com.learning.vo.FriendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * FriendServiceImpl
 * Created by lw on 16-12-23.
 */
@Service
public class FriendServiceImpl implements IFriendService {
    @Autowired
    private FriendDomainMapper friendDomainMapper;

    public List<FriendVo> getFriendByLoginName(String loginName) {
        List<FriendDomain> friendDomainList = friendDomainMapper.selectByLoginName(loginName);
        List<FriendVo> friendVoList = new ArrayList<FriendVo>(friendDomainList.size());
        for (FriendDomain friendDomain : friendDomainList) {
            FriendVo friendVo = new FriendVo();
            friendVo.setLoginName(friendDomain.getLoginNameB());
            friendVoList.add(friendVo);
        }
        return friendVoList;
    }
}
