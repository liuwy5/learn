package com.learning.controller.ui.customer;

import com.learning.common.enums.LearnLevelEnum;
import com.learning.common.enums.LearnTypeEnum;
import com.learning.common.util.TicketUtil;
import com.learning.domain.ArticleDomain;
import com.learning.service.ArticleServiceImpl;
import com.learning.vo.ArticleVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 *
 * Created by liuw on 17-5-3.
 */
@Controller
@RequestMapping("/customer")
public class CustomerArticleController {

    private ArticleServiceImpl articleService = new ArticleServiceImpl();

    @RequestMapping("/articleList/{type}/{level}")
    public String articleList(Model model, @PathVariable("type") String type, @PathVariable("level") String level, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);
        model.addAttribute("learnType", type);
        model.addAttribute("learnLevel", level);

        model.addAttribute("periodList", articleService.periodList(type, level));
        return "customer/articleList";
    }

    @RequestMapping("/articleDetail/{learnType}/{learnLevel}/{period}")
    public String articleDetail(Model model, @PathVariable("learnType") String learnType, @PathVariable("learnLevel") String learnLevel,
                                @PathVariable("period") String period, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);
        System.out.println("learnType: " + learnType + ", learnLevel: " + learnLevel + ", period: " + period);

        ArticleDomain articleDomain = new ArticleDomain();
        articleDomain.setTitle("title");
        articleDomain.setContent("content");
        articleDomain.setFirstQuestion("f");
        articleDomain.setFirstA("first a");

        model.addAttribute("article", articleDomain);
        return "customer/article";
    }
}
