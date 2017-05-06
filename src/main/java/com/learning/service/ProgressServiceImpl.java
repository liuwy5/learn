package com.learning.service;

import com.learning.dao.ProgressDao;
import com.learning.domain.ProgressDomain;

import java.util.List;

public class ProgressServiceImpl {

    private ArticleServiceImpl articleService = new ArticleServiceImpl();

    public ProgressDomain selectProgress(String loginName, String type, String level) {
        return ProgressDao.select(loginName, type, level);
//        if (progressDomain == null) {
//            List<Integer> periodList = articleService.periodList(type, level);
//            if (periodList.size() == 0) {
//                return null;
//            }
//            progressDomain = new ProgressDomain();
//            progressDomain.setLoginName(loginName);
//            progressDomain.setType(type);
//            progressDomain.setLevel(level);
//            progressDomain.setPeriod(periodList.get(0).toString());
//            progressDomain.setNum(1);
//        }
    }
}
