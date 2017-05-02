package com.learning.controller.ui.admin;

import com.learning.annotations.Login;
import com.learning.common.enums.LearnLevelEnum;
import com.learning.common.enums.LearnTypeEnum;
import com.learning.service.ArticleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by liuw on 17-4-27.
 */
@Controller
@RequestMapping("admin/article")
public class ArticleController {

    private ArticleServiceImpl articleService = new ArticleServiceImpl();

    @RequestMapping("/{learnType}/{learnLevel}")
    @Login
    public String articleList(Model model, @PathVariable("learnType") String learnType, @PathVariable("learnLevel") String learnLevel) {
        LearnTypeEnum learnTypeEnum = LearnTypeEnum.valueOfCode(learnType);
        if (learnTypeEnum != null) {
            model.addAttribute("learnType", learnTypeEnum);
        }

        LearnLevelEnum learnLevelEnum = LearnLevelEnum.valueOfCode(learnLevel);
        if (learnLevelEnum != null) {
            model.addAttribute("learnLevel", learnLevelEnum);
        }

        model.addAttribute("periodList", articleService.toFillPeriodList(learnType, learnLevel));
        model.addAttribute("articleList", articleService.articleList(learnType, learnLevel));

        return "admin/article";
    }
}
