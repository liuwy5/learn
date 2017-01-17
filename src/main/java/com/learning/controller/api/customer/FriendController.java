package com.learning.controller.api.customer;

import com.learning.bean.Resp;
import com.learning.service.IFriendService;
import com.learning.vo.FriendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by liuw on 17-1-17.
 */
@RequestMapping("/friend")
@RestController
public class FriendController {
    @Autowired
    private IFriendService friendService;

    @RequestMapping("/add")
    public Resp add(FriendVo friendVo) {
        return friendService.add(friendVo);
    }
}
