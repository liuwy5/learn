package com.learning.service;

import com.learning.dao.HistoryDao;
import com.learning.domain.HistoryDomain;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl {
    public HistoryDomain select(String loginName, String type, String level, String period, Integer num) {
        return HistoryDao.select(loginName, type, level, period, num);
    }
}
