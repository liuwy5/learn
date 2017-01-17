package com.learning.service;

import com.learning.bean.Resp;
import com.learning.vo.FriendVo;

import java.util.List;

/**
 *
 * Created by lw on 16-12-23.
 */
public interface IFriendService {
    List<FriendVo> getFriendByLoginName(String loginName);

    Integer isFriend(String loginName, String friendName);

    Resp add(FriendVo friendVo);
}
