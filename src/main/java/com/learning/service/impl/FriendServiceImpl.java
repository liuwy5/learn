package com.learning.service.impl;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.common.util.UuidUtil;
import com.learning.domain.FriendDomain;
import com.learning.persistence.FriendDomainMapper;
import com.learning.service.IFriendService;
import com.learning.vo.FriendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public Integer isFriend(String loginName, String friendName) {
        FriendDomain friendDomain = friendDomainMapper.selectByLoginNameAndFriendName(loginName, friendName);
        if (friendDomain == null) {
            return 0;
        } else {
            return 1;
        }
    }

    @Transactional
    public Resp add(FriendVo friendVo) {
        FriendDomain friendDomain = new FriendDomain();
        friendDomain.setLoginNameA(friendVo.getLoginName());
        friendDomain.setLoginNameB(friendVo.getFriendLoginName());
        friendDomain.setUuid(UuidUtil.getUuid());
        friendDomainMapper.insertSelective(friendDomain);
        friendDomain.setLoginNameA(friendVo.getFriendLoginName());
        friendDomain.setLoginNameB(friendVo.getLoginName());
        friendDomain.setUuid(UuidUtil.getUuid());
        friendDomainMapper.insertSelective(friendDomain);
        return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
    }
}
