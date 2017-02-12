package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.AdminDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by liuw on 17-1-6.
 */
public class AdminDao {

    private static Logger logger = LoggerFactory.getLogger(AdminDao.class);

    public static List<AdminDomain> selectAllAdmin() {
        String sqlString = "select * from admin order by id desc ";
        logger.info("execute sql AdminDao.selectAllAdmin: " + sqlString);
        return packageObjects(sqlString);
    }

    public static AdminDomain selectByLoginName(String loginName) {
        String sqlString = "select * from admin where login_name = '" + loginName + "'";
        logger.info("execute sql AdminDao.selectByLoginName: " + sqlString);
        return packageObject(sqlString);
    }

    public static AdminDomain selectByPrimaryKey(Integer id) {
        String sqlString = "select * from admin where id = " + id;
        logger.info("execute sql AdminDao.selectByPrimaryKey: " + sqlString);
        return packageObject(sqlString);
    }

    public static void add(AdminDomain adminDomain) {
        String formatter = "insert into admin (login_name, password, name, role_id, role_name) values ('%s', '%s', '%s', %d, '%s') ";
        String sqlString = String.format(formatter, adminDomain.getLoginName(), adminDomain.getPassword(), adminDomain.getName(), adminDomain.getRoleId(), adminDomain.getRoleName());
        logger.info("execute sql AdminDao.add: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void updateById(AdminDomain adminDomain) {
        String formatter = "update admin set login_name = '%s', password = '%s', name = '%s', role_id = %d, role_name = '%s' where id = %d";
        String sqlString = String.format(formatter, adminDomain.getLoginName(), adminDomain.getPassword(), adminDomain.getName(),
                adminDomain.getRoleId(), adminDomain.getRoleName(), adminDomain.getId());
        logger.info("execute sql AdminDao.updateById: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void deleteById(Integer id) {
        String sqlString = "delete from admin where id = " + id;
        logger.info("execute sql AdminDao.deleteById: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    private static List<AdminDomain> packageObjects(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        List<AdminDomain> adminDomainList = new ArrayList<AdminDomain>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    AdminDomain adminDomain = new AdminDomain();
                    adminDomain.setId(resultSet.getInt("id"));
                    adminDomain.setLoginName(resultSet.getString("login_name"));
                    adminDomain.setPassword(resultSet.getString("password"));
                    adminDomain.setName(resultSet.getString("name"));
                    adminDomain.setRoleId(resultSet.getInt("role_id"));
                    adminDomain.setRoleName(resultSet.getString("role_name"));
                    adminDomainList.add(adminDomain);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return adminDomainList;
    }

    private static AdminDomain packageObject(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        try {
            if (resultSet != null && resultSet.next()) {
                AdminDomain adminDomain = new AdminDomain();
                adminDomain.setId(resultSet.getInt("id"));
                adminDomain.setLoginName(resultSet.getString("login_name"));
                adminDomain.setPassword(resultSet.getString("password"));
                adminDomain.setName(resultSet.getString("name"));
                adminDomain.setRoleId(resultSet.getInt("role_id"));
                adminDomain.setRoleName(resultSet.getString("role_name"));
                return adminDomain;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
