package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.PasswdDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 * Created by liuw on 17-1-7.
 */
public class PasswdDao {
    private static Logger logger = LoggerFactory.getLogger(PasswdDao.class);

    public static PasswdDomain selectByLoginName(String loginName) {
        String sqlString = "select * from passwd where login_name = '" + loginName + "'";
        logger.info("execute sql PasswdDao.selectByLoginName: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        try {
            if (resultSet != null && resultSet.next()) {
                PasswdDomain passwdDomain = new PasswdDomain();
                passwdDomain.setId(resultSet.getInt("id"));
                passwdDomain.setLoginName(resultSet.getString("login_name"));
                passwdDomain.setPassword(resultSet.getString("password"));
                passwdDomain.setName(resultSet.getString("name"));
                passwdDomain.setGender(resultSet.getInt("gender"));
                passwdDomain.setTel(resultSet.getString("tel"));
                passwdDomain.setEmail(resultSet.getString("email"));
                passwdDomain.setOnLine(resultSet.getInt("on_line"));
                passwdDomain.setNational(resultSet.getInt("national"));
                passwdDomain.setInterest(resultSet.getString("interest"));
                return passwdDomain;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void updatePasswordByPrimaryKey(PasswdDomain record) {
        String sqlString = "update passwd set password = '" + record.getPassword() + "' where id = " + record.getId();
        logger.info("execute sql PasswdDao.updatePasswordByPrimaryKey: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }
}
