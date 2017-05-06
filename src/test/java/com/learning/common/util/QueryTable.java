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
            System.out.println(resultSet.getInt("id") + ":" + resultSet.getString("login_name") + ": " + resultSet.getString("password") + ": " +
                    resultSet.getString("name") + ": " + resultSet.getString("gender") + ": " + resultSet.getString("tel") + ": " +
                    resultSet.getString("email") + ": " + resultSet.getString("national") + ": " + resultSet.getString("interest"));
        }
    }

    @Test
    public void queryInterest() throws Exception {
        String sqlString = "select * from interest order by created_at desc";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getInt("interest") + ">>"
                 + resultSet.getString("group_chat_name"));
            }

        }
    }

    @Test
    public void queryRole() throws Exception {
        String sqlString = "select * from role order by id desc";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("no") + ">>"
                        + resultSet.getString("name"));
            }

        }
    }

    @Test
    public void queryPrivilege() throws Exception {
        String sqlString = "select * from privilege order by id desc";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name") + ">>"
                        + resultSet.getString("url") + ">>" + resultSet.getInt("num"));
            }

        }
    }

    @Test
    public void queryRolePrivilegeMapping() throws Exception {
        String sqlString = "select * from role_privilege_mapping order by id desc";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("role_no") + ">>"
                        + resultSet.getInt("privilege_id"));
            }

        }
    }

    @Test
    public void queryArticle() throws Exception {
        String sqlString = "select * from article";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title") + ">>"
                        + resultSet.getString("content") + " period: " + resultSet.getInt("period") + " num: " +
                resultSet.getInt("num"));
            }

        }
    }

    @Test
    public void queryProgress() throws Exception {
        String sqlString = "select * from progress";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("login_name") + ">>"
                        + resultSet.getString("type") + " level: " + resultSet.getString("level") + " num: " +
                        resultSet.getInt("num"));
            }

        }
    }

    @Test
    public void queryHistory() throws Exception {
        String sqlString = "select * from history";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("login_name") + ">>"
                        + resultSet.getString("type") + " level: " + resultSet.getInt("level") +
                        " period: " + resultSet.getString("period") + " num: " + resultSet.getInt("num") +
                        "first: " + resultSet.getString("first") + " " + resultSet.getString("second") +
                resultSet.getString("third") + " " + resultSet.getString("fourth") + " " + resultSet.getString("fifth"));
            }

        }
    }

    @Test
    public void query() throws Exception {
        String sqlString = "select p.* from admin a, role r, role_privilege_mapping m, privilege p " +
                "where login_name = 'admin1' and a.role_id = r.id and r.no = m.role_no and m.privilege_id = p.id order by p.id ";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id") + ": " + resultSet.getString("name") + ": " +
                    resultSet.getString("url") + ": " + resultSet.getInt("num"));
        }
    }

    @Test
    public void t() {
        String s = "1";
        System.out.println(Integer.parseInt(s));
    }
}
