package com.learning.service;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.dao.ArticleDao;
import com.learning.dao.HistoryDao;
import com.learning.dao.ProgressDao;
import com.learning.domain.ArticleDomain;
import com.learning.domain.HistoryDomain;
import com.learning.domain.ProgressDomain;
import com.learning.vo.HistoryVo;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl {
    public HistoryDomain select(String loginName, String type, String level, String period, Integer num) {
        return HistoryDao.select(loginName, type, level, period, num);
    }

    public Resp insertOrUpdate(HistoryVo historyVo) {
        HistoryDomain historyDomain = select(historyVo.getLoginName(), historyVo.getType(), historyVo.getLevel(), historyVo.getPeriod(), historyVo.getNum());
        if (historyDomain == null) {
            historyDomain = new HistoryDomain();
            historyDomain.setLoginName(historyVo.getLoginName());
            historyDomain.setType(historyVo.getType());
            historyDomain.setLevel(historyVo.getLevel());
            historyDomain.setPeriod(historyVo.getPeriod());
            historyDomain.setNum(historyVo.getNum());
            historyDomain.setFirst(historyVo.getFirst());
            historyDomain.setSecond(historyVo.getSecond());
            historyDomain.setThird(historyVo.getThird());
            historyDomain.setFourth(historyVo.getFourth());
            historyDomain.setFifth(historyVo.getFifth());
            HistoryDao.insert(historyDomain);
        } else {
            HistoryDao.update(historyDomain.getId(), historyVo.getFirst(), historyVo.getSecond(), historyVo.getThird(),
                    historyVo.getFourth(), historyVo.getFifth());
        }

        ArticleDomain articleDomain = ArticleDao.select(historyVo.getType(), historyVo.getLevel(), historyVo.getPeriod(), historyVo.getNum());
        if (articleDomain != null) {
            int count = 0;
            if (historyVo.getFirst().equals(articleDomain.getFirstAnswer())) {
                count++;
            }
            if (historyVo.getSecond().equals(articleDomain.getSecondAnswer())) {
                count++;
            }
            if (historyVo.getThird().equals(articleDomain.getThirdAnswer())) {
                count++;
            }
            if (historyVo.getFourth().equals(articleDomain.getFourthAnswer())) {
                count++;
            }
            if (historyVo.getFifth().equals(articleDomain.getFifthAnswer())) {
                count++;
            }

            System.out.println("count: " + count);

            if (count >= 3) {
                // update progress
                ProgressDomain progressDomain = ProgressDao.select(historyVo.getLoginName(), historyVo.getType(), historyVo.getLevel());
                if (progressDomain != null) {
                    if (Integer.valueOf(progressDomain.getPeriod()) < Integer.valueOf(historyVo.getPeriod()) ||
                            (progressDomain.getPeriod().equals(historyVo.getPeriod()) && progressDomain.getNum() < historyVo.getNum())) {
                        ProgressDao.update(progressDomain.getId(), historyVo.getPeriod(), historyVo.getNum());
                    }
                } else {
                    progressDomain = new ProgressDomain();
                    progressDomain.setLoginName(historyVo.getLoginName());
                    progressDomain.setType(historyVo.getType());
                    progressDomain.setLevel(historyVo.getLevel());
                    progressDomain.setPeriod(historyVo.getPeriod());
                    progressDomain.setNum(historyVo.getNum());
                    ProgressDao.insert(progressDomain);
                }
                return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
            } else {
                return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "学习结果不达标，请重新学习");
            }


        }


        return new Resp(RespStatusEnum.RESP_FAIL.getCode(), "找不到相关文章");
    }


}
