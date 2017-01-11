CREATE database learning;
use learning;
-- passwd
drop TABLE if exists passwd;
CREATE TABLE passwd(
  id int(11) not null auto_increment,
  uuid varchar(36) not null DEFAULT '',
  login_name varchar(10) COLLATE utf8_bin not null default '' comment '登录名',
  password varchar(15) COLLATE utf8_bin not null DEFAULT '' comment '密码',
  name varchar(10) COLLATE utf8_bin not null default '' comment '姓名',
  gender int(1) COLLATE utf8_bin not null DEFAULT 0 comment '性别：0 男 1 女',
  tel varchar(20) COLLATE utf8_bin not null default '',
  email varchar(30) COLLATE utf8_bin not null default '',
  on_line int(1) COLLATE utf8_bin not null DEFAULT 0 comment '是否在线：0 离线 1 在线',
  national int(1) COLLATE utf8_bin not null DEFAULT 0 comment '国籍：0 中国 1 非中国',
  interest varchar(30) COLLATE utf8_bin null default '' comment '兴趣：0 体育 1 电影 2 音乐；分号分隔',
  created_at datetime COLLATE utf8_bin NULL,
  primary key (id),
  unique key idx_passwd_login_name (login_name)
) ENGINE=InnoDB default CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户名表';
insert into passwd (uuid, login_name, password, name, gender, tel, email, national, interest)
values
(uuid(), 'rose', '000000', '玫瑰', 1, '131', 'ab@qq.com', 0, '0;1'),
(uuid(), 'lavender', '000000', '薰衣草', 1, '131', 'ab@qq.com', 0, '0;1'),
(uuid(), 'violet', '000000', '紫罗兰', 1, '131', 'ab@qq.com', 0, '0;1');

-- admin
drop table if exists admin;
create table admin (
  id int(11) not null auto_increment,
  uuid varchar(36) not null default '',
  login_name varchar(10) COLLATE utf8_bin not null default '' comment '登录名',
  password varchar(15) COLLATE utf8_bin not null default '' comment '密码',
  created_at datetime COLLATE utf8_bin NULL,
  role_uuid varchar(36) collate utf8_bin not null default '',
  PRIMARY KEY (id),
  UNIQUE KEY idx_admin_login_name (login_name)
) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin comment='系统管理员表';
insert INTO admin (uuid, login_name, password, role_uuid) VALUES
(uuid(), 'admin1', '000000', uuid()), (uuid(), 'admin2', '000000', uuid());

-- role
drop table if exists role;
create table role(
  id int(11) not null auto_increment,
  uuid varchar(36) not null default '',
  role_name varchar(10) COLLATE utf8_bin not null default '' comment '角色名',
  created_at datetime COLLATE utf8_bin NULL,
  PRIMARY KEY (id)
) engine=InnoDB default CHARSET=utf8 COLLATE=utf8_bin comment='角色表';
insert into role(uuid, role_name, created_at) values (uuid(), '管理员', now()), (uuid(), '操作员', now());

-- privilege
drop table if exists privilege;
create table privilege(
  id int(11) not null auto_increment,
  uuid varchar(36) not null default '',
  privilege_no varchar(10) COLLATE utf8_bin not null default '' comment '权限号',
  privilege_name varchar(10) COLLATE utf8_bin not null default '' comment '权限名',
  created_at datetime COLLATE utf8_bin NULL,
  PRIMARY KEY (id),
  UNIQUE KEY idx_privilege_no (privilege_no)
) engine=InnoDB default CHARSET=utf8 COLLATE=utf8_bin comment='权限表';
insert into privilege(uuid, privilege_no, privilege_name, created_at) values
(uuid(), 'customer', '客户管理', now()), (uuid(), 'culture', '文化管理', now()), (uuid(), 'admin', '账号管理', now());

-- role_privilege_mapping
drop table if exists role_privilege_mapping;
create table role_privilege_mapping(
  id int(11) not null auto_increment,
  uuid varchar(36) not null default '',
  role_uuid varchar(10) COLLATE utf8_bin not null default '' comment '角色uuid',
  privilege_uuid varchar(10) COLLATE utf8_bin not null default '' comment '权限uuid',
  privilege_no varchar(10) COLLATE utf8_bin not null default '' comment '权限号',
  privilege_name varchar(10) COLLATE utf8_bin not null default '' comment '权限名',
  created_at datetime COLLATE utf8_bin NULL,
  PRIMARY KEY (id)
) engine=InnoDB default CHARSET=utf8 COLLATE=utf8_bin comment='角色权限映射表';

-- message
drop table if exists message;
create table message(
  id int(11) not null auto_increment,
  uuid varchar(36) not null default '',
  sender varchar(10) COLLATE utf8_bin not null default '' comment '发起方',
  receiver varchar(10) COLLATE utf8_bin not null default '' comment '接收方',
  send int(1) COLLATE utf8_bin not null DEFAULT 0 comment '是否为发送方：0 发送 1 接收',
  interest int(5) null DEFAULT null comment '是否为私聊，null为私聊，否则为群聊id',
  content varchar(1000) COLLATE utf8_bin not null default '' comment '发送内容',
  hasRead int(1) COLLATE utf8_bin not null DEFAULT 0 comment '是否已读：0 未读 1 已读',
  created_at datetime COLLATE utf8_bin NULL,
  PRIMARY KEY (id)
) engine=InnoDB default CHARSET=utf8 COLLATE=utf8_bin comment='聊天记录表';

-- culture
drop table if exists culture;
create table culture(
  id int(11) not null auto_increment,
  uuid varchar(36) not null default '',
  title varchar(100) COLLATE utf8_bin not null default '' comment '标题',
  content text COLLATE utf8_bin null comment '内容',
  created_at datetime COLLATE utf8_bin NULL,
  PRIMARY KEY (id)
) engine=InnoDB default CHARSET=utf8 COLLATE=utf8_bin comment='文化展示表';

-- interest
drop table if exists interest;
create table interest(
  id int(11) not null auto_increment,
  uuid varchar(36) not null default '',
  interest int(11) COLLATE utf8_bin null default 0 comment '兴趣：0 体育 1 电影 3 音乐，分号分隔',
  created_at datetime COLLATE utf8_bin NULL,
  PRIMARY KEY (id)
) engine=InnoDB default CHARSET=utf8 COLLATE=utf8_bin comment='兴趣群聊表';

-- friend
drop table if exists friend;
create table friend(
  id int(11) not null auto_increment,
  uuid varchar(36) not null default '',
  login_name_a varchar(10) COLLATE utf8_bin not null default '' comment '好友 主',
  login_name_b varchar(10) COLLATE utf8_bin not null default '' comment '好友b',
  created_at datetime COLLATE utf8_bin NULL,
  PRIMARY KEY (id)
) engine=InnoDB default CHARSET=utf8 COLLATE=utf8_bin comment='兴趣群聊表';
1
INSERT INTO friend (uuid, login_name_a, login_name_b, created_at) VALUES
(uuid(), 'rose', 'lavender', now()), (uuid(), 'lavender', 'rose', now()),
(uuid(), 'rose', 'violet', now()), (uuid(), 'violet', 'rose', now());

