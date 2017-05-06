package com.learning.controller.ui.customer;

import com.learning.common.util.TicketUtil;
import com.learning.dao.ArticleDao;
import com.learning.domain.ArticleDomain;
import com.learning.domain.HistoryDomain;
import com.learning.domain.ProgressDomain;
import com.learning.service.ArticleServiceImpl;
import com.learning.service.HistoryServiceImpl;
import com.learning.service.ProgressServiceImpl;
import com.learning.vo.PeriodVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

        // periodList
        List<Integer> periodList = articleService.periodList(type, level);
        List<PeriodVo> periodVoList = new ArrayList<PeriodVo>();
        ProgressDomain progressDomain = progressService.selectProgress(loginName, type, level);
        for (Integer period : periodList) {
            PeriodVo periodVo = new PeriodVo();
            periodVo.setPeriod(period);

            Integer hasDone = 0;
            if (progressDomain != null) {
                if (Integer.valueOf(progressDomain.getPeriod()) > period) {
                    hasDone = 1;
                } else if (Integer.valueOf(progressDomain.getPeriod()).equals(period)) {
                    ArticleDomain nextArticle = articleService.selectNext(type, level, period.toString(), progressDomain.getNum());
                    if (nextArticle == null || Integer.valueOf(nextArticle.getPeriod()) > period) {
                        hasDone = 1;
                    }
                }
            }
            periodVo.setHasDone(hasDone);
            periodVoList.add(periodVo);
        }
        model.addAttribute("periodList", periodVoList);

        if (progressDomain != null) {
            model.addAttribute("periodProgress", progressDomain.getPeriod());
            model.addAttribute("numProgress", progressDomain.getNum());
        } else {
            model.addAttribute("periodProgress", 1);
            model.addAttribute("numProgress", 1);
        }
        return "customer/articleList";
    }

    @RequestMapping("/articleDetail/{learnType}/{learnLevel}/{period}/{answer}/{selfAnswer}")
    public String articleDetail(Model model, @PathVariable("learnType") String learnType, @PathVariable("learnLevel") String learnLevel,
                                @PathVariable("period") String period,
                                @PathVariable("answer") Integer answer, @PathVariable("selfAnswer")Integer selfAnswer, HttpSession session) {
        String loginName = TicketUtil.getLoginName(session);
        ProgressDomain progressDomain = progressService.selectProgress(loginName, learnType, learnLevel);
        Integer num;
        if (progressDomain != null) {

            // 已通过
            if (Integer.parseInt(progressDomain.getPeriod()) != Integer.parseInt(period)) {
                num = 1;
            } else {
                num = (progressDomain.getNum() + 1) % 3;
            }
        } else {
            num = 1;
        }
        ArticleDomain articleDomain = articleService.detail(learnType, learnLevel, period, num);
        model.addAttribute("article", articleDomain);
        s(loginName, learnType, learnLevel, period, num, model);
        model.addAttribute("answer", answer);
        model.addAttribute("selfAnswer", selfAnswer);
        String url = "/customer/articleDetail/" + learnType + "/" + learnLevel + "/" + period + "/" + num + "/" + answer + "/" + selfAnswer;
        model.addAttribute("url", url);

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
        String url = "/customer/articleDetail/" + learnType + "/" + learnLevel + "/" + period + "/" + num + "/" + answer + "/" + selfAnswer;
        model.addAttribute("url", url);

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

        Integer seeAnswer = historyDomain != null ? 1 : 0;
        model.addAttribute("seeAnswer", seeAnswer);

        ArticleDomain lastArticle = articleService.selectLast(learnType, learnLevel, period, num);
        model.addAttribute("hasLast", lastArticle == null ? 0 : 1);
        if (lastArticle != null) {
            model.addAttribute("lastPeriod", lastArticle.getPeriod());
            model.addAttribute("lastNum", lastArticle.getNum());
        }

        ArticleDomain nextArticle = articleService.selectNext(learnType, learnLevel, period, num);
        model.addAttribute("hasNext", nextArticle == null ? 0 : 1);
        if (nextArticle != null) {
            model.addAttribute("nextPeriod", nextArticle.getPeriod());
            model.addAttribute("nextNum", nextArticle.getNum());
        }

//        Integer hasNext = 0;
//        ProgressDomain progressDomain = progressService.selectProgress(loginName, learnType, learnLevel);
//        if (progressDomain != null) {
//            if (Integer.valueOf(progressDomain.getPeriod()) > Integer.valueOf(period) ||
//                    (progressDomain.getPeriod().equals(period) && progressDomain.getNum() >= num)) {
//                ArticleDomain nextArticle = articleService.selectNext(learnType, learnLevel, period, num);
//                if (nextArticle != null) {
//                    hasNext = 1;
//                }
//
//            }
//        }
//        model.addAttribute("hasNext", hasNext);


        Integer canSubmit = 0;
        ProgressDomain progressDomain = progressService.selectProgress(loginName, learnType, learnLevel);
        if (progressDomain != null) {
            if (Integer.valueOf(progressDomain.getPeriod()) > Integer.valueOf(period) ||
                    (progressDomain.getPeriod().equals(period) && progressDomain.getNum() + 1 >= num)) {
//                if (nextArticle != null) {
//                    canSubmit = 1;
//                }
                canSubmit = 1;
            }
        } else {
            List<Integer> periodList = articleService.periodList(learnType, learnLevel);
            if (periodList.size() > 0 && periodList.get(0).equals(Integer.valueOf(period)) && num == 1) {
                canSubmit = 1;
            }
        }
        model.addAttribute("canSubmit", canSubmit);
    }
}
