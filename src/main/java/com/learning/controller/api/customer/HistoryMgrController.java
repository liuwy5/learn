package com.learning.controller.api.customer;

import com.learning.bean.Resp;
import com.learning.common.util.TicketUtil;
import com.learning.service.HistoryServiceImpl;
import com.learning.vo.HistoryVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/customer/history")
public class HistoryMgrController {

    private HistoryServiceImpl historyService = new HistoryServiceImpl();

    @RequestMapping("/insertOrUpdate")
    public Resp insertOrUpdate(HistoryVo historyVo, HttpSession httpSession) {
        String loginName = TicketUtil.getLoginName(httpSession);
        historyVo.setLoginName(loginName);
        return historyService.insertOrUpdate(historyVo);
    }
}
