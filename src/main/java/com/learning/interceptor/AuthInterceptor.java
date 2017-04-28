package com.learning.interceptor;

import com.learning.bean.Ticket;
import com.learning.common.enums.LearnLevelEnum;
import com.learning.common.enums.LearnTypeEnum;
import com.learning.common.util.TicketUtil;
import com.learning.dao.PrivilegeDao;
import com.learning.domain.PrivilegeDomain;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 权限控制
 * Created by liuw on 17-2-16.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        Ticket ticket = TicketUtil.getTicket(session);
        if (ticket != null) {
            String loginName = ticket.getLoginName();
            List<PrivilegeDomain> privilegeDomainList = PrivilegeDao.selectPrivilegeByLoginName(loginName);
            if (modelAndView != null) {
                modelAndView.addObject("privilegeList", privilegeDomainList);
                modelAndView.addObject("learnTypeList", LearnTypeEnum.values());
                modelAndView.addObject("learnLevelList", LearnLevelEnum.values());
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
