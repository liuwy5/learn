CREATE TABLE passwd(
  id int(11) primary key auto_increment,
  login_name varchar(10) default '' comment '登录名',
  password varchar(15) DEFAULT '' comment '密码',
  name varchar(10) default '' comment '姓名',
  gender int(1) DEFAULT 0 comment '性别：0 男 1 女',
  tel varchar(20) default '',
  email varchar(30) default '',
  on_line int(1) DEFAULT 0 comment '是否在线：0 离线 1 在线',
  national int(1) DEFAULT 0 comment '国籍：0 中国 1 非中国',
  interest varchar(30) null default '' comment '兴趣：0 体育 1 电影 2 音乐；分号分隔',
  created_at datetime NULL
) COMMENT='用户名表';
insert into passwd (login_name, password, name, gender, tel, email, national, interest)
values
('rose', '000000', '玫瑰', 1, '131', 'ab@qq.com', 0, '0;1'),
('lavender', '000000', '薰衣草', 1, '131', 'ab@qq.com', 0, '0;1'),
('violet', '000000', '紫罗兰', 1, '131', 'ab@qq.com', 0, '0;1');

create table if not EXISTS admin (
  id int(5) PRIMARY key auto_increment,
  login_name varchar(10) default '' comment '登录名',
  password varchar(15) default '' comment '密码',
  name VARCHAR(20) NULL comment 'name',
  created_at datetime NULL,
  role_id int(5) NULL,
  role_name VARCHAR(20) null
) comment='系统管理员表';
insert INTO admin (login_name, password, name, role_id, role_name) VALUES ('admin1', '000000', '管理', 1, '管理员'), ('admin2', '000000', '操作', 2, '操作员');

create table if not EXISTS friend(
  id int(5) PRIMARY key auto_increment,
  login_name_a varchar(10) default '' comment '好友主',
  login_name_b varchar(10) default '' comment '好友b',
  created_at datetime NULL
) comment='好友表';
INSERT INTO friend (login_name_a, login_name_b, created_at) VALUES
('rose', 'lavender', now()), ('lavender', 'rose', now()),
('rose', 'violet', now()), ('violet', 'rose', now());

create table if not EXISTS interest(
  id int(5) primary key auto_increment,
  interest int(5) default 0 comment '兴趣：0 体育 1 电影 3 音乐，分号分隔',
  group_chat_name varchar(10) default '' comment '群聊名称',
  created_at datetime NULL
) comment='兴趣群聊表';

create table message(
  id int(15) PRIMARY KEY auto_increment,
  sender varchar(10) default '' comment '发起方',
  receiver varchar(10) default NULL comment '接收方',
  send int(1) DEFAULT 0 comment '是否为发送方：1 发送 0 接收',
  interest int(5) DEFAULT NULL comment '是否为私聊，null为私聊，否则为群聊id',
  content varchar(1000) default '' comment '发送内容',
  hasRead int(1) DEFAULT 0 comment '是否已读：0 未读 1 已读',
  created_at datetime NULL
) comment='聊天记录表';

-- culture
drop table if exists culture;
create table if not EXISTS culture(
  id int(11) PRIMARY KEY auto_increment,
  title varchar(100) not null default '' comment '标题',
  content text null comment '内容',
  created_at VARCHAR(20) NULL
) comment='文化展示表';

drop table if exists role;
create table if not exists role (
  id int(10) PRIMARY key auto_increment,
  no varchar(10) null,
  name varchar(10) null
) comment='角色表';


drop table if exists privilege;
create table if not exists privilege (
  id int(10) primary key auto_increment,
  name varchar(20) null,
  url varchar(50) null,
  num int(3) null
);
insert into privilege (name, url, num) values ('注册用户管理', '/admin/register/customer', 1), ('系统用户管理', '/admin/system/customer', 2),
('文化内容管理', '/admin/culture', 3), ('角色管理', '/admin/role', 4);

drop table if exists role_privilege_mapping;
CREATE TABLE if not EXISTS role_privilege_mapping (
  id int(10) primary key auto_increment,
  role_no varchar(10),
  privilege_id int(10)
);

drop table if exists article;
CREATE TABLE article (
  id int(10) PRIMARY KEY auto_increment,
  type varchar(20),
  level VARCHAR(20),
  period VARCHAR(2),
  num INT(3),
  title VARCHAR(50),
  content varchar(5000),
  first_question varchar(200),
  first_a varchar(100),
  first_b varchar(100),
  first_c varchar(100),
  first_d varchar(100),
  first_answer varchar(1),
  first_explain varchar(100),
  second_question varchar(200),
  second_a varchar(100),
  second_b varchar(100),
  second_c varchar(100),
  second_d varchar(100),
  second_answer varchar(1),
  second_explain varchar(100),
  third_question varchar(200),
  third_a varchar(100),
  third_b varchar(100),
  third_c varchar(100),
  third_d varchar(100),
  third_answer varchar(1),
  third_explain varchar(100),
  fourth_question varchar(200),
  fourth_a varchar(100),
  fourth_b varchar(100),
  fourth_c varchar(100),
  fourth_d varchar(100),
  fourth_answer varchar(1),
  fourth_explain varchar(100),
  fifth_question varchar(200),
  fifth_a varchar(100),
  fifth_b varchar(100),
  fifth_c varchar(100),
  fifth_d varchar(100),
  fifth_answer varchar(1),
  fifth_explain varchar(100),
);

DROP TABLE if EXISTS progress;
CREATE TABLE progress (
  id int(10) PRIMARY KEY auto_increment,
  login_name varchar(20),
  type varchar(20),
  level VARCHAR(20),
  period VARCHAR(2),
  num INT(3)
);

DROP TABLE if EXISTS history;
CREATE TABLE history (
  id int(10) PRIMARY KEY auto_increment,
  login_name varchar(20),
  type varchar(20),
  level VARCHAR(20),
  period VARCHAR(2),
  num INT(3),
  first varchar(2),
  second varchar(2),
  third varchar(2),
  fourth varchar(2),
  fifth varchar(2)
);