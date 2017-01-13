package com.learning.controller.ui.customer;

import com.learning.common.enums.ChatModeEnum;
import com.learning.common.util.TicketUtil;
import com.learning.dao.InterestDao;
import com.learning.domain.InterestDomain;
import com.learning.service.MessageServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 *
 * Created by lw on 16-12-16.
 */
@Controller
@RequestMapping("/chat")
public class ChatController {
    private MessageServiceImpl messageService = new MessageServiceImpl();

    @RequestMapping("/{friendLoginName}")
    public String chat(@PathVariable("friendLoginName") String friendLoginName, Model model, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);

        model.addAttribute("loginName", loginName);
        model.addAttribute("friendLoginName", friendLoginName);
        model.addAttribute("chatMode", ChatModeEnum.PRIVATE_CHAT.getCode());
        model.addAttribute("privateMessageList", messageService.selectPrivateMessage(loginName, friendLoginName));

        return "customer/chat";
    }

    @RequestMapping("/group/{interestId}")
    public String groupChat(@PathVariable("interestId") Integer interestId, Model model, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);

        InterestDomain interestDomain = InterestDao.selectById(interestId);
        model.addAttribute("loginName", loginName);
        if (interestDomain != null) {
            model.addAttribute("groupChatName", interestDomain.getGroupChatName());
            model.addAttribute("interestName", interestDomain.getInterestName());
        } else {
            model.addAttribute("groupChatName", null);
            model.addAttribute("interestName", null);
        }
        model.addAttribute("chatMode", ChatModeEnum.GROUP_CHAT.getCode());
        model.addAttribute("interestId", interestId);

        model.addAttribute("groupMessageList", messageService.selectGroupMessage(interestId, loginName));
        return "customer/chat";
    }
}
