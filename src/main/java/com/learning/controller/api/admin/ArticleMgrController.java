package com.learning.controller.api.admin;

import com.learning.bean.Resp;
import com.learning.domain.ArticleDomain;
import com.learning.service.ArticleServiceImpl;
import com.learning.vo.ArticleVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by liuw on 17-4-30.
 */
@RestController
@RequestMapping("/article")
public class ArticleMgrController {

    private ArticleServiceImpl articleService = new ArticleServiceImpl();

    @RequestMapping("/detail")
    public ArticleDomain detail(Integer id) {
        return articleService.detail(id);
    }

    @RequestMapping("/add")
    public Resp add(ArticleVo articleVo) {
        return articleService.addArticle(articleVo);
    }

    @RequestMapping("/update")
    public Resp update(ArticleVo articleVo) {
        return articleService.update(articleVo);
    }

    @RequestMapping("/delete")
    public Resp delete(Integer id) {
        return articleService.deleteById(id);
    }

    @RequestMapping("/articleList")
    public ArticleVo s(String learnType, String learnLevel) {
        ArticleVo articleVo = new ArticleVo();
        articleVo.setType(learnType);
        articleVo.setLevel(learnLevel);
        return articleVo;
    }

}
