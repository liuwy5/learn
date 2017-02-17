package com.learning.common.util;

import org.junit.Test;

import java.sql.ResultSet;

/**
 *
 * Created by liuw on 17-1-6.
 */
public class QueryTable {
    @Test
    public void queryAdmin() throws Exception {
        String sqlString = "select * from admin";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        while (resultSet.next()){
            System.out.println(resultSet.getString("login_name") + ": " + resultSet.getString("password"));
        }
    }

    @Test
    public void queryPasswd() throws Exception {
        String sqlString = "select * from passwd";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        while (resultSet.next()){
            System.out.println(resultSet.getString("login_name") + ": " + resultSet.getString("password") + ": " +
                    resultSet.getString("name") + ": " + resultSet.getString("gender") + ": " + resultSet.getString("tel") + ": " +
                    resultSet.getString("email") + ": " + resultSet.getString("national") + ": " + resultSet.getString("interest"));
        }
    }

    @Test
    public void query() throws Exception {
        String sqlString = "select p.* from admin a, role r, role_privilege_mapping m, privilege p " +
                "where login_name = 'admin2' and a.role_id = r.id and r.no = m.role_no and m.privilege_id = p.id order by p.id ";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id") + ": " + resultSet.getString("name") + ": " +
                    resultSet.getString("url") + ": " + resultSet.getInt("num"));
        }
    }
}
