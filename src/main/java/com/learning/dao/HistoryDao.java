package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.HistoryDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDao {

    private static Logger logger = LoggerFactory.getLogger(HistoryDao.class);

    public static HistoryDomain select(String loginName, String type, String level, String period, Integer num) {
        String formatter = "select * from history where login_name = '%s' and type = '%s' and level = '%s' and period = '%s' " +
                "and num = %d";
        String sqlString = String.format(formatter, loginName, type, level, period, num);
        logger.info("execute sql HistoryDao.select: " + sqlString);
        return packageDomain(sqlString);
    }

    private static HistoryDomain packageDomain(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        try {
            if (resultSet != null && resultSet.next()) {
                HistoryDomain historyDomain = new HistoryDomain();
                historyDomain.setId(resultSet.getInt("id"));
                historyDomain.setLoginName(resultSet.getString("login_name"));
                historyDomain.setType(resultSet.getString("type"));
                historyDomain.setLevel(resultSet.getString("level"));
                historyDomain.setPeriod(resultSet.getString("period"));
                historyDomain.setNum(resultSet.getInt("num"));
                historyDomain.setFirst(resultSet.getString("first"));
                historyDomain.setSecond(resultSet.getString("second"));
                historyDomain.setThird(resultSet.getString("third"));
                historyDomain.setFourth(resultSet.getString("fourth"));
                historyDomain.setFifth(resultSet.getString("fifth"));
                return historyDomain;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
