package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.common.util.TimeUtil;
import com.learning.domain.MessageDomain;
import com.learning.vo.MessageVo;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by liuw on 17-1-11.
 */
public class MessageDao {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AdminDao.class);

    public static void insertPrivateMessage(MessageVo messageVo) {
        String sqlString = "insert into message (sender, receiver, content, created_at) values " +
                "('" + messageVo.getSender() + "', '" + messageVo.getReceiver() + "', '" + messageVo.getContent().replace("'", "") + "', '" +
                TimeUtil.getDateNormalNow() + "')";
        logger.info("execute sql MessageDao.insertPrivateMessage: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void insertGroupMessage(MessageVo messageVo) {
        String sqlString = "insert into message (sender, interest, content, created_at) values " +
                "('" + messageVo.getSender() + "', " + messageVo.getInterestId() + ", '" +
                messageVo.getContent().replace("'", "") + "', '" + TimeUtil.getDateNormalNow() + "')";
        logger.info("execute sql MessageDao.insertGroupMessage: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static List<MessageDomain> selectPrivateMessage(String sender, String receiver) {
        String sqlString = "select * from message where " +
                "(sender = '" + sender + "' and receiver = '" + receiver + "') or (sender = '" + receiver + "' and receiver = '" + sender + "') " +
                "order by created_at, id";
        logger.info("execute sql MessageDao.selectPrivateMessage: " + sqlString);
        List<MessageDomain> messageDomainList = new ArrayList<MessageDomain>();
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    MessageDomain messageDomain = new MessageDomain();
                    String send = resultSet.getString("sender");
                    String receive = resultSet.getString("receiver");
                    if (sender.equals(send)) {
                        messageDomain.setSender(send);
                        messageDomain.setReceiver(receive);
                        messageDomain.setSend(1);
                    } else {
                        messageDomain.setSender(receive);
                        messageDomain.setReceiver(send);
                        messageDomain.setSend(0);
                    }
                    messageDomain.setContent(resultSet.getString("content"));
                    messageDomain.setCreatedAt(resultSet.getString("created_at"));
                    messageDomainList.add(messageDomain);
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return messageDomainList;
    }

    public static List<MessageDomain> selectGroupMessage(Integer interestId, String loginName) {
        String sqlString = "select * from message where interest = " + interestId + "order by created_at, id ";
        logger.info("execute sql MessageDao.selectGroupMessage: " + sqlString);
        List<MessageDomain> messageDomainList = new ArrayList<MessageDomain>();
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    MessageDomain messageDomain = new MessageDomain();
                    String send = resultSet.getString("sender");
                    messageDomain.setSender(send);
                    if (loginName.equals(send)) {
                        messageDomain.setSend(1);
                    } else {
                        messageDomain.setSend(0);
                    }
                    messageDomain.setContent(resultSet.getString("content"));
                    messageDomain.setCreatedAt(resultSet.getString("created_at"));
                    messageDomainList.add(messageDomain);
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return messageDomainList;
    }
}
