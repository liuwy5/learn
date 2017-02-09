package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.FriendDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by liuw on 17-1-7.
 */
public class FriendDao {
    private static Logger logger = LoggerFactory.getLogger(AdminDao.class);

    public static List<FriendDomain> selectByLoginName(String loginName) {
        String sqlString = "select * from friend where login_name_a = '" + loginName + "'";
        logger.info("execute sql FriendDao.selectByLoginName: " + sqlString);
        List<FriendDomain> friendDomainList = new ArrayList<FriendDomain>();
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    FriendDomain friendDomain = new FriendDomain();
                    friendDomain.setLoginNameA(resultSet.getString("login_name_a"));
                    friendDomain.setLoginNameB(resultSet.getString("login_name_b"));
                    friendDomainList.add(friendDomain);
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return friendDomainList;
    }

    public static FriendDomain selectByLoginNameAndFriendName(String loginName, String friendName) {
        String formatter = "SELECT * from friend where login_name_a = '%s' and login_name_b = '%s' ";
        String sqlString = String.format(formatter, loginName, friendName);
        logger.info("execute sql FriendDao.selectByLoginNameAndFriendName: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        try {
            if (resultSet != null && resultSet.next()) {
                FriendDomain friendDomain = new FriendDomain();
                friendDomain.setLoginNameA(resultSet.getString("login_name_a"));
                friendDomain.setLoginNameB(resultSet.getString("login_name_b"));
                return friendDomain;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void insertFriend(FriendDomain friendDomain) {
        String formatter = "insert into friend (login_name_a, login_name_b, created_at) values ('%s', '%s', now()) ";
        String sqlString = String.format(formatter, friendDomain.getLoginNameA(), friendDomain.getLoginNameB());
        logger.info("execute sql FriendDao.insertFriend: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }
}
