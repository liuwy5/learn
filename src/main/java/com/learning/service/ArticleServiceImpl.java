package com.learning.service;

import com.learning.dao.ArticleDao;
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
}
