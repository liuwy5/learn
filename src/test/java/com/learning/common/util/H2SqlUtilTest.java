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
        String sqlString = "delete from passwd where id in (4, 5, 6, 7, 8, 9);";
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
