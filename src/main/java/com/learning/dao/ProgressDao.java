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

    private static ProgressDomain packageDomain(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    ProgressDomain progressDomain = new ProgressDomain();
                    progressDomain.setId(resultSet.getInt("id"));
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
