package com.learning.common.util;

import org.junit.Test;

import java.sql.ResultSet;

/**
 *
 * Created by liuw on 17-1-6.
 */
public class CreateTable {
    /**
     * 创建friend表
     */
    @Test
    public void createPasswd() throws Exception{
        String sqlString = "CREATE TABLE passwd(\n" +
                "  id int(11) primary key auto_increment,\n" +
                "  login_name varchar(10) default '' comment '登录名',\n" +
                "  password varchar(15) DEFAULT '' comment '密码',\n" +
                "  name varchar(10) default '' comment '姓名',\n" +
                "  gender int(1) DEFAULT 0 comment '性别：0 男 1 女',\n" +
                "  tel varchar(20) default '',\n" +
                "  email varchar(30) default '',\n" +
                "  on_line int(1) DEFAULT 0 comment '是否在线：0 离线 1 在线',\n" +
                "  national int(1) DEFAULT 0 comment '国籍：0 中国 1 非中国',\n" +
                "  interest varchar(30) null default '' comment '兴趣：0 体育 1 电影 2 音乐；分号分隔',\n" +
                "  created_at datetime NULL\n" +
                ") COMMENT='用户名表';";
        H2SqlUtil.updateSql(sqlString);
        sqlString = "insert into passwd (login_name, password, name, gender, tel, email, national, interest)\n" +
                "values\n" +
                "('rose', '000000', '玫瑰', 1, '131', 'ab@qq.com', 0, '0;1'),\n" +
                "('lavender', '000000', '薰衣草', 1, '131', 'ab@qq.com', 0, '0;1'),\n" +
                "('violet', '000000', '紫罗兰', 1, '131', 'ab@qq.com', 0, '0;1');";
        H2SqlUtil.updateSql(sqlString);
        sqlString = "select * from passwd";
        System.out.println("id> login_name_a  login_name_b");
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id") + "> " + resultSet.getString("login_name") + ": " +
                    resultSet.getString("password"));
        }
    }

    /**
     * 创建admin表
     */
    @Test
    public void createAdmin() throws Exception{
        String sqlString = "drop table admin; create table if not EXISTS admin (\n" +
                "  id int(5) PRIMARY key auto_increment,\n" +
                "  login_name varchar(10) default '' comment '登录名',\n" +
                "  password varchar(15) default '' comment '密码',\n" +
                "  created_at datetime NULL,\n" +
                "  role_id int(5) not null default '',\n" +
                ") comment='系统管理员表';";
        H2SqlUtil.updateSql(sqlString);
        sqlString = "insert INTO admin (login_name, password, role_id) VALUES ('admin1', '000000', 1), ('admin2', '000000', 2);";
        H2SqlUtil.updateSql(sqlString);
        sqlString = "select * from admin";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        while (resultSet.next()){
            System.out.println(resultSet.getString("login_name") + ": " + resultSet.getString("password"));
        }
    }

    /**
     * 创建friend表
     */
    @Test
    public void createFriend() throws Exception{
        String sqlString = "create table if not EXISTS friend(\n" +
                "  id int(5) PRIMARY key auto_increment,\n" +
                "  login_name_a varchar(10) default '' comment '好友主',\n" +
                "  login_name_b varchar(10) default '' comment '好友b',\n" +
                "  created_at datetime NULL\n" +
                ") comment='好友表';";
        H2SqlUtil.updateSql(sqlString);
        sqlString = "INSERT INTO friend (login_name_a, login_name_b, created_at) VALUES\n" +
                "('rose', 'lavender', now()), ('lavender', 'rose', now()),\n" +
                "('rose', 'violet', now()), ('violet', 'rose', now());";
        H2SqlUtil.updateSql(sqlString);
        sqlString = "select * from friend";
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        System.out.println("id> login_name_a  login_name_b");
        while (resultSet.next()){
            System.out.println(resultSet.getInt("id") + "> " + resultSet.getString("login_name_a") + ": " + resultSet.getString("login_name_b"));
        }
    }

    /**
     * 创建message聊天记录表
     */
    @Test
    public void createMessage() throws Exception {
        String sqlString = "drop table message; create table message(\n" +
                "  id int(15) PRIMARY KEY auto_increment,\n" +
                "  sender varchar(10) default '' comment '发起方',\n" +
                "  receiver varchar(10) default NULL comment '接收方',\n" +
                "  send int(1) DEFAULT 0 comment '是否为发送方：0 发送 1 接收',\n" +
                "  interest int(5) DEFAULT NULL comment '是否为私聊，null为私聊，否则为群聊id',\n" +
                "  content varchar(1000) default '' comment '发送内容',\n" +
                "  hasRead int(1) DEFAULT 0 comment '是否已读：0 未读 1 已读',\n" +
                "  created_at varchar(20) NULL\n" +
                ") comment='聊天记录表';";
        H2SqlUtil.updateSql(sqlString);
    }

    /**
     * 创建interest表
     */
    @Test
    public void createInterest() throws Exception {
        String sqlString = "drop table interest; create table if not EXISTS interest(\n" +
                "  id int(5) primary key auto_increment,\n" +
                "  interest int(5) default 0 comment '兴趣：0 体育 1 电影 3 音乐，分号分隔',\n" +
                "  group_chat_name varchar(10) default '' comment '群聊名称',\n" +
                "  created_at datetime NULL\n" +
                ") comment='兴趣群聊表';";
        H2SqlUtil.updateSql(sqlString);
    }

    /**
     * 创建culture表
     */
    @Test
    public void createCulture() throws Exception {
        String sqlString = "drop table if exists culture;\n" +
                "create table if not EXISTS culture(\n" +
                "  id int(11) PRIMARY KEY auto_increment,\n" +
                "  title varchar(100) not null default '' comment '标题',\n" +
                "  content text null comment '内容',\n" +
                "  created_at VARCHAR(20) NULL\n" +
                ") comment='文化展示表';";
        H2SqlUtil.updateSql(sqlString);
    }
}
