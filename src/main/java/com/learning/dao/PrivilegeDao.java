package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.PrivilegeDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by liuw on 17-2-13.
 */
public class PrivilegeDao {

    private static Logger logger = LoggerFactory.getLogger(PasswdDao.class);

    public static List<PrivilegeDomain> selectAllPrivilege() {
        String sqlString = "select * from privilege";
        logger.info("execute sql PrivilegeDao.selectAllPrivilege: " + sqlString);
        return packageObjects(sqlString);
    }

    private static List<PrivilegeDomain> packageObjects(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        List<PrivilegeDomain> privilegeDomainList = new ArrayList<PrivilegeDomain>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    PrivilegeDomain privilegeDomain = new PrivilegeDomain();
                    privilegeDomain.setId(resultSet.getInt("id"));
                    privilegeDomain.setName(resultSet.getString("name"));
                    privilegeDomain.setUrl(resultSet.getString("url"));
                    privilegeDomain.setNum(resultSet.getInt("num"));
                    privilegeDomainList.add(privilegeDomain);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return privilegeDomainList;
    }
}
