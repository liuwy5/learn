package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.AdminDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * Created by liuw on 17-1-6.
 */
public class AdminDao {

    private static Logger logger = LoggerFactory.getLogger(AdminDao.class);

    public static AdminDomain selectByLoginName(String loginName) {
        String sqlString = "select * from admin where login_name = '" + loginName + "'";
        logger.info("execute sql AdminDao.selectByLoginName: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        try {
            if (resultSet != null && resultSet.next()) {
                AdminDomain adminDomain = new AdminDomain();
                adminDomain.setLoginName(resultSet.getString("login_name"));
                adminDomain.setPassword(resultSet.getString("password"));
                return adminDomain;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
