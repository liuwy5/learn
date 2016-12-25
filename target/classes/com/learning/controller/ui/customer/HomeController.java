package com.learning.controller.ui.customer;

import com.learning.common.util.TicketUtil;
import com.learning.service.IFriendService;
import com.learning.vo.FriendVo;
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

    @RequestMapping("")
    public String home(Model model, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);
        if (loginName != null) {
            List<FriendVo> friendVoList = friendService.getFriendByLoginName(loginName);
            model.addAttribute("friendList", friendVoList);
            model.addAttribute("friendCount", friendVoList.size());
        }
        return "customer/index";
    }
}
