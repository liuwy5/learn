package com.learning.dao;

import com.learning.common.enums.InterestTypeEnum;
import com.learning.common.util.H2SqlUtil;
import com.learning.domain.InterestDomain;
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
public class InterestDao {
    private static Logger logger = LoggerFactory.getLogger(InterestDao.class);

    public static InterestDomain selectById(Integer id) {
        String sqlString = "select * from interest where id = 4";
        logger.info("execute sql InterestDao.selectById: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        try {
            if (resultSet != null && resultSet.next()) {
                InterestDomain interestDomain = new InterestDomain();
                interestDomain.setId(resultSet.getInt("id"));
                interestDomain.setInterest(resultSet.getInt("interest"));
                InterestTypeEnum interestTypeEnum = InterestTypeEnum.valueOfCode(interestDomain.getInterest());
                if (interestTypeEnum != null) {
                    interestDomain.setInterestName(interestTypeEnum.getMean());
                }
                interestDomain.setGroupChatName(resultSet.getString("group_chat_name"));
                return interestDomain;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static List<InterestDomain> selectAllInterest() {
        String sqlString = "select * from interest order by created_at desc";
        logger.info("execute sql InterestDao.selectAllInterest: " + sqlString);
        List<InterestDomain> interestDomainList = new ArrayList<InterestDomain>();
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    InterestDomain interestDomain = new InterestDomain();
                    interestDomain.setId(resultSet.getInt("id"));
                    interestDomain.setInterest(resultSet.getInt("interest"));
                    InterestTypeEnum interestTypeEnum = InterestTypeEnum.valueOfCode(interestDomain.getInterest());
                    if (interestTypeEnum != null) {
                        interestDomain.setInterestName(interestTypeEnum.getMean());
                    }
                    interestDomain.setGroupChatName(resultSet.getString("group_chat_name"));
                    interestDomainList.add(interestDomain);
                }

            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return interestDomainList;
    }

    public static void addInterest(String chatName, Integer interestType) {
        String sqlString = "insert into interest (interest, group_chat_name, created_at) values " +
                "(" + interestType + ", '" + chatName + "', now())";

        logger.info("execute sql InterestDao.addInterest: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }
}
