package com.learning.controller.ui.customer;

import com.learning.common.enums.ChatModeEnum;
import com.learning.dao.InterestDao;
import com.learning.domain.InterestDomain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by lw on 16-12-16.
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    @RequestMapping("/{friendLoginName}")
    public String chat(@PathVariable("friendLoginName") String friendLoginName, Model model) {
        model.addAttribute("friendLoginName", friendLoginName);
        model.addAttribute("chatMode", ChatModeEnum.PRIVATE_CHAT.getCode());
        return "customer/chat";
    }

    @RequestMapping("/group/{interestId}")
    public String groupChat(@PathVariable("interestId") Integer interestId, Model model) {
        InterestDomain interestDomain = InterestDao.selectById(interestId);
        if (interestDomain != null) {
            model.addAttribute("groupChatName", interestDomain.getGroupChatName());
            model.addAttribute("interestName", interestDomain.getInterestName());
        } else {
            model.addAttribute("groupChatName", null);
            model.addAttribute("interestName", null);
        }
        model.addAttribute("chatMode", ChatModeEnum.GROUP_CHAT.getCode());
        model.addAttribute("interestId", interestId);
        return "customer/chat";
    }
}
