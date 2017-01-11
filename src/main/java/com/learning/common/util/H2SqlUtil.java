package com.learning.common.util;

import org.h2.tools.Server;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 *
 * Created by liuw on 17-1-6.
 */
public class H2SqlUtil {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(H2SqlUtil.class);

    private static Server server;

    static {
        String port = "9094";
        try {
            logger.info("正在启动h2...");
            server = org.h2.tools.Server.createTcpServer(
                    new String[] { "-tcpPort", port }).start();
        } catch (SQLException e) {
            logger.error("启动h2出错：" + e.toString());
            e.printStackTrace();
        }
    }

    public static void updateSql(String sqlString) {
        Connection connection = connectionThreadLocal.get();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(sqlString);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public static ResultSet querySql(String sqlString) {
        Connection connection = connectionThreadLocal.get();
        Statement statement;
        try {
            statement = connection.createStatement();
            return statement.executeQuery(sqlString);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void stopServer() {
        if (server != null) {
            logger.info("正在关闭h2...");
            server.stop();
            logger.info("关闭成功.");
        }
    }

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>(){
        String dbDir = "./h2db/sample.sql";
        String user = "sa";
        String password = "123456";

        @Override
        protected Connection initialValue() {
            logger.debug("connectionThreadLocal initialValue()");
            try {
                Class.forName("org.h2.Driver");
                return DriverManager.getConnection("jdbc:h2:" + dbDir, user, password);
            } catch (ClassNotFoundException e) {
                logger.error("initialValue()",e);
                e.printStackTrace();
            } catch (SQLException e) {
                logger.error("initialValue()",e);
                e.printStackTrace();
            }
            return null;
        };
    };

}
