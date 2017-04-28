package com.learning.common.util;

import org.junit.Test;

import java.sql.ResultSet;

/**
 *
 * Created by liuw on 17-1-6.
 */
public class H2SqlUtilTest {
    @Test
    public void updateSqlTest() throws Exception{
        Integer interestType = 3;
        String chatName = "1234567";
        String sqlString = "insert into role_privilege_mapping (role_no, privilege_id) values ('operator', 5), ('admin', 5);";
//        String sqlString = "insert into privilege (name, url, num) values ('学习内容管理', '/admin/article', 5)";
        H2SqlUtil.updateSql(sqlString);


//        sqlString = "select * from message";
//        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
//        while (resultSet.next()){
//            System.out.println(resultSet.getInt("id") + ">" + resultSet.getInt("interest") + ": " +
//                    resultSet.getString("group_chat_name") + "//" + resultSet.getTimestamp("created_at"));
//        }
    }

    @Test
        public void querySqlTest() throws Exception {  // select * from interest where id = 4
        String sqlString = "select * from passwd";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id") + ">" + resultSet.getInt("interest") + ": " +
                    resultSet.getString("group_chat_name") + "//" + resultSet.getTimestamp("created_at"));
        }
    }
}
