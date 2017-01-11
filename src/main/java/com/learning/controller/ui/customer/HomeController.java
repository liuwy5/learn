package com.learning.controller.ui.customer;

import com.learning.common.enums.InterestTypeEnum;
import com.learning.common.util.TicketUtil;
import com.learning.dao.InterestDao;
import com.learning.domain.InterestDomain;
import com.learning.service.IFriendService;
import com.learning.service.IPasswdService;
import com.learning.vo.FriendVo;
import com.learning.vo.InterestFriendVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * Created by lw on 16-12-21.
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private IFriendService friendService;

    @Autowired
    private IPasswdService passwdService;

    @RequestMapping("")
    public String home(Model model, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);
        if (loginName != null) {
            List<FriendVo> friendVoList = friendService.getFriendByLoginName(loginName);
            model.addAttribute("friendList", friendVoList);
            model.addAttribute("friendCount", friendVoList.size());
        }

        // 非好友列表
        List<InterestFriendVo> interestFriendList = passwdService.getInterestFriend(loginName);
        model.addAttribute("interestFriendList", interestFriendList);

        // 群聊列表
        List<InterestDomain> interestDomainList = InterestDao.selectAllInterest();
        model.addAttribute("interestChatList", interestDomainList);
        model.addAttribute("interestChatCount", interestDomainList.size());

        // 兴趣类别
        model.addAttribute("interestList", InterestTypeEnum.values());

        return "/customer/index";
    }
}
