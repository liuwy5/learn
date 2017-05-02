package com.learning.service;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.dao.ArticleDao;
import com.learning.domain.ArticleDomain;
import com.learning.vo.ArticleVo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by liuw on 17-4-28.
 */
public class ArticleServiceImpl {
    public List<Integer> toFillPeriodList(String articleType, String articleLevel) {
        List<Integer> fullPeriodList = ArticleDao.fullPeriodList(articleType, articleLevel);
        List<Integer> toFillPeriodList = new ArrayList<Integer>();
        int length = 17;
        int j = 0;
        if (fullPeriodList.size() > 0) {
            for (int i = 1; i <= length; i++) {
                while (j < fullPeriodList.size() - 1 && i > fullPeriodList.get(j)) {
                    j++;
                }
                if (i != fullPeriodList.get(j)) {
                    toFillPeriodList.add(i);
                }
            }
        } else {
            for (int i = 1; i <= length; i++) {
                toFillPeriodList.add(i);
            }
        }

        return toFillPeriodList;
    }

    public List<ArticleDomain> articleList(String type, String level) {
        return ArticleDao.articleList(type, level);
    }

    public ArticleDomain detail(Integer id) {
        return ArticleDao.detail(id);
    }

    public Resp addArticle(ArticleVo articleVo) {
        ArticleDomain articleDomain = ArticleDao.selectByTitle(articleVo.getType(), articleVo.getLevel(), articleVo.getTitle());
        if (articleDomain != null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该文章已存在");
        } else {
            articleVo.setNum(ArticleDao.selectMaxNum(articleVo.getType(), articleVo.getLevel(), articleVo.getPeriod()) + 1);
            ArticleDao.add(articleVo);
            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), "添加成功");
        }
    }

    public Resp update(ArticleVo articleVo) {
        ArticleDomain articleDomain = ArticleDao.selectById(articleVo.getId());
        if (articleDomain == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该文章不存在");
        } else {
            ArticleDao.updateById(articleVo);
            return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
        }
    }

    public Resp deleteById(Integer id) {
        ArticleDomain articleDomain = ArticleDao.selectById(id);
        if (articleDomain == null) {
            return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "该文章不存在");
        } else {
            for (int i = articleDomain.getNum() + 1; i <= 3; i++) {
                ArticleDomain articleDomain1 = ArticleDao.select(articleDomain.getType(), articleDomain.getLevel(), articleDomain.getPeriod(), i);
                if (articleDomain1 != null) {
                    ArticleDao.updateNumById(articleDomain1.getId(), articleDomain1.getNum() - 1);
                }
            }
            ArticleDao.deleteById(id);
        }
        return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
    }
}
