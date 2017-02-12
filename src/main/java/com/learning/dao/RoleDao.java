package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.RoleDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by liuw on 17-2-10.
 */
public class RoleDao {
    private static Logger logger = LoggerFactory.getLogger(RoleDao.class);

    public static List<RoleDomain> selectAllRole() {
        String sqlString = "select * from role";
        logger.info("execute sql RoleDao.selectAllRole: " + sqlString);
        return packageObjects(sqlString);
    }

    public static RoleDomain selectById(Integer id) {
        String sqlString = "select * from role where id = " + id;
        logger.info("execute sql RoleDao.selectById: " + sqlString);
        return packageObject(sqlString);
    }

    private static List<RoleDomain> packageObjects(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        List<RoleDomain> roleDomainList = new ArrayList<RoleDomain>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    RoleDomain roleDomain = new RoleDomain();
                    roleDomain.setId(resultSet.getInt("id"));
                    roleDomain.setName(resultSet.getString("name"));
                    roleDomainList.add(roleDomain);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return roleDomainList;
    }

    private static RoleDomain packageObject(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    RoleDomain roleDomain = new RoleDomain();
                    roleDomain.setId(resultSet.getInt("id"));
                    roleDomain.setName(resultSet.getString("name"));
                    return roleDomain;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
