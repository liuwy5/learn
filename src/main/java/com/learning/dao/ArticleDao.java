package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * ArticleDao
 * Created by liuw on 17-4-28.
 */
public class ArticleDao {

    private static Logger logger = LoggerFactory.getLogger(AdminDao.class);

    public static List<Integer> fullPeriodList(String articleType, String articleLevel) {
        String sqlString = "select a.period, count(*) count from article a where a.type = '" + articleType + "' and a.level = '" + articleLevel + "' " +
                "group by a.period having count >= 3 order by a.period asc";
        logger.info("execute sql ArticleDao.periodList: " + sqlString);
        List<Integer> periodList = new ArrayList<Integer>();
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    periodList.add(resultSet.getInt("period"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return periodList;
    }
}
