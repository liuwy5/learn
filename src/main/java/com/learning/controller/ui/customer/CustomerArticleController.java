package com.learning.controller.ui.customer;

import com.learning.common.util.TicketUtil;
import com.learning.dao.ArticleDao;
import com.learning.domain.ArticleDomain;
import com.learning.domain.HistoryDomain;
import com.learning.domain.ProgressDomain;
import com.learning.service.ArticleServiceImpl;
import com.learning.service.HistoryServiceImpl;
import com.learning.service.ProgressServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequestMapping("/customer")
public class CustomerArticleController {

    private ArticleServiceImpl articleService = new ArticleServiceImpl();

    private ProgressServiceImpl progressService = new ProgressServiceImpl();

    private HistoryServiceImpl historyService = new HistoryServiceImpl();

    @RequestMapping("/articleList/{type}/{level}")
    public String articleList(Model model, @PathVariable("type") String type, @PathVariable("level") String level, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);
        model.addAttribute("learnType", type);
        model.addAttribute("learnLevel", level);

        model.addAttribute("periodList", articleService.periodList(type, level));

        ProgressDomain progressDomain = progressService.selectProgressNotNull(loginName, type, level);
        if (progressDomain != null) {
            model.addAttribute("periodProgress", progressDomain.getPeriod());
            model.addAttribute("numProgress", progressDomain.getNum());
        }
        return "customer/articleList";
    }

    @RequestMapping("/articleDetail/{learnType}/{learnLevel}/{period}/{answer}/{selfAnswer}")
    public String articleDetail(Model model, @PathVariable("learnType") String learnType, @PathVariable("learnLevel") String learnLevel,
                                @PathVariable("period") String period,
                                @PathVariable("answer") Integer answer, @PathVariable("selfAnswer")Integer selfAnswer, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);
        ProgressDomain progressDomain = progressService.selectProgressNotNull(loginName, learnType, learnLevel);
        if (progressDomain != null) {
            Integer num;
            // 已通过
            if (Integer.parseInt(progressDomain.getPeriod()) > Integer.parseInt(period)) {
                num = 1;
            } else {
                num = progressDomain.getNum();
            }
            ArticleDomain articleDomain = articleService.detail(learnType, learnLevel, period, num);

            if (articleDomain != null) {
                articleDomain.setContents(Arrays.asList(articleDomain.getContent().split("\n")));
            }
            model.addAttribute("article", articleDomain);

            s(loginName, learnType, learnLevel, period, num, model);
            model.addAttribute("answer", answer);
            model.addAttribute("selfAnswer", selfAnswer);
        }

        return "customer/article";
    }

    @RequestMapping("/articleDetail/{learnType}/{learnLevel}/{period}/{num}/{answer}/{selfAnswer}")
    public String articleDetailNum(Model model, @PathVariable("learnType") String learnType, @PathVariable("learnLevel") String learnLevel,
                                @PathVariable("period") String period, @PathVariable("num") Integer num,
                                @PathVariable("answer") Integer answer, @PathVariable("selfAnswer")Integer selfAnswer, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);

        ArticleDomain articleDomain = articleService.detail(learnType, learnLevel, period, num);
        model.addAttribute("article", articleDomain);

        s(loginName, learnType, learnLevel, period, num, model);
        model.addAttribute("answer", answer);
        model.addAttribute("selfAnswer", selfAnswer);

        return "customer/article";
    }

    @RequestMapping("/articleDetail/last/{learnType}/{learnLevel}/{period}/{num}/{answer}/{selfAnswer}")
    public String lastArticleDetail(Model model, @PathVariable("learnType") String learnType, @PathVariable("learnLevel") String learnLevel,
                                @PathVariable("period") String period, @PathVariable("num") Integer num,
                                    @PathVariable("answer") Integer answer, @PathVariable("selfAnswer")Integer selfAnswer, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);

        ArticleDomain articleDomain = articleService.selectLast(learnType, learnLevel, period, num);
        model.addAttribute("article", articleDomain);

        s(loginName, learnType, learnLevel, period, num, model);
        model.addAttribute("answer", answer);
        model.addAttribute("selfAnswer", selfAnswer);

        return "customer/article";
    }

    @RequestMapping("/articleDetail/next/{learnType}/{learnLevel}/{period}/{num}/{answer}/{selfAnswer}")
    public String nextArticleDetail(Model model, @PathVariable("learnType") String learnType, @PathVariable("learnLevel") String learnLevel,
                                    @PathVariable("period") String period, @PathVariable("num") Integer num,
                                    @PathVariable("answer") Integer answer, @PathVariable("selfAnswer")Integer selfAnswer, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);

        ArticleDomain articleDomain = articleService.selectNext(learnType, learnLevel, period, num);
        model.addAttribute("article", articleDomain);

        s(loginName, learnType, learnLevel, period, num, model);
        model.addAttribute("answer", answer);
        model.addAttribute("selfAnswer", selfAnswer);

        return "customer/article";
    }



    public void s (String loginName, String learnType, String learnLevel, String period, Integer num, Model model) {
        HistoryDomain historyDomain = historyService.select(loginName, learnType, learnLevel, period, num);
        model.addAttribute("history", historyDomain);

        ArticleDomain lastArticle = articleService.selectLast(learnType, learnLevel, period, num);
        model.addAttribute("hasLast", lastArticle == null ? 0 : 1);

        Integer hasNext = 0;
        ProgressDomain progressDomain = progressService.selectProgressNotNull(loginName, learnType, learnLevel);
        if (Integer.valueOf(progressDomain.getPeriod()) > Integer.valueOf(period) ||
                (progressDomain.getPeriod().equals(period) && progressDomain.getNum() > num)) {
            hasNext = 1;
        }
        model.addAttribute("hasNext", hasNext);
    }
}
