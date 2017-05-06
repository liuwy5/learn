package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.ProgressDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressDao {

    private static Logger logger = LoggerFactory.getLogger(PasswdDao.class);

    public static ProgressDomain select(String loginName, String type, String level) {
        String formatter = "select * from progress where login_name = '%s' and type = '%s' and level = '%s'";
        String sqlString = String.format(formatter, loginName, type, level);
        logger.info("execute sql ProgressDao.select: " + sqlString);
        return packageDomain(sqlString);
    }

    public static void insert(ProgressDomain progressDomain) {
        String formatter = "insert into progress (login_name, type, level, period, num) " +
                "values ('%s', '%s', '%s', '%s', %d)";
        String sqlString = String.format(formatter, progressDomain.getLoginName(), progressDomain.getType(), progressDomain.getLevel(),
                progressDomain.getPeriod(), progressDomain.getNum());
        logger.info("execute sql ProgressDao.insert: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void update(Integer id, String period, Integer num) {
        String formatter = "update progress set period = '%s', num = %d where id = %d";
        String sqlString = String.format(formatter, period, num, id);
        logger.info("execute sql ProgressDao.update: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    private static ProgressDomain packageDomain(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    ProgressDomain progressDomain = new ProgressDomain();
                    progressDomain.setId(resultSet.getInt("id"));
                    progressDomain.setLoginName(resultSet.getString("login_name"));
                    progressDomain.setType(resultSet.getString("type"));
                    progressDomain.setLevel(resultSet.getString("level"));
                    progressDomain.setPeriod(resultSet.getString("period"));
                    progressDomain.setNum(resultSet.getInt("num"));
                    return progressDomain;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
