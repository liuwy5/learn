package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.PrivilegeDomain;
import com.learning.domain.RoleDomain;
import com.learning.vo.RoleVo;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by liuw on 17-2-10.
 */
public class RoleDao {
    private static Logger logger = LoggerFactory.getLogger(RoleDao.class);

    public static List<RoleDomain> selectAllRole() {
        String sqlString = "select * from role";
        logger.info("execute sql RoleDao.selectAllRole: " + sqlString);
        return packageObjects(sqlString);
    }

    public static RoleDomain selectById(Integer id) {
        String sqlString = "select * from role where id = " + id;
        logger.info("execute sql RoleDao.selectById: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    RoleDomain roleDomain = new RoleDomain();
                    roleDomain.setId(resultSet.getInt("id"));
                    roleDomain.setNo(resultSet.getString("no"));
                    roleDomain.setName(resultSet.getString("name"));

                    List<PrivilegeDomain> privilegeDomainList = new ArrayList<PrivilegeDomain>();
                    sqlString = "select p.id, p.name, p.url, p.num " +
                            "from privilege p, role_privilege_mapping m where p.id = m.privilege_id and m.role_no = '" + resultSet.getString("no") + "'";
                    logger.info("execute sql RoleDao.selectById <privilege> : " + sqlString);
                    resultSet = H2SqlUtil.querySql(sqlString);
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            PrivilegeDomain privilegeDomain = new PrivilegeDomain();
                            privilegeDomain.setId(resultSet.getInt("id"));
                            privilegeDomain.setName(resultSet.getString("name"));
                            privilegeDomain.setUrl(resultSet.getString("url"));
                            privilegeDomain.setNum(resultSet.getInt("num"));
                            privilegeDomainList.add(privilegeDomain);
                        }
                    }
                    roleDomain.setPrivilegeDomainList(privilegeDomainList);
                    return roleDomain;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static RoleDomain selectByName(String name) {
        String sqlString = "select * from role where name = '" + name + "'";
        logger.info("execute sql RoleDao.selectByName: " + sqlString);
        return packageObject(sqlString);
    }

    public static RoleDomain selectByNameExcept(String name, Integer id) {
        String sqlString = "select * from role where name = '" + name + "' and id != " + id;
        logger.info("execute sql RoleDao.selectByName: " + sqlString);
        return packageObject(sqlString);
    }

    public static void add(RoleVo roleVo) {
        String formatter = "insert into role (no, name) values ('%s', '%s')";
        String sqlString = String.format(formatter, roleVo.getNo(), roleVo.getName());
        logger.info("execute sql RoleDao.add: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
        for (Integer privilegeId : roleVo.getPrivilegeIdList()) {
            sqlString = "insert into role_privilege_mapping (role_no, privilege_id) values ('" + roleVo.getNo() + "', " + privilegeId + ")";
            logger.info("execute sql RoleDao.add: <role_privilege_mapping> " + sqlString);
            H2SqlUtil.updateSql(sqlString);
        }
    }

    public static void update(RoleVo roleVo) {
        String sqlString = "update role set name = '" + roleVo.getName() + "' where no = '" + roleVo.getNo() + "'";
        H2SqlUtil.updateSql(sqlString);
        sqlString = "delete from role_privilege_mapping where role_no = '" + roleVo.getNo() + "'";
        logger.info("execute sql RoleDao.update: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
        for (Integer privilegeId : roleVo.getPrivilegeIdList()) {
            sqlString = "insert into role_privilege_mapping (role_no, privilege_id) values ('" + roleVo.getNo() + "', " + privilegeId + ")";
            logger.info("execute sql RoleDao.update: <role_privilege_mapping> " + sqlString);
            H2SqlUtil.updateSql(sqlString);
        }
    }

    public static void delete(Integer id) {
        String sqlString = "select * from role where id = " + id;
        logger.info("execute sql RoleDao.delete: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        String roleNo = null;
        try {
            if (resultSet != null && resultSet.next()) {
                roleNo = resultSet.getString("no");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlString = "delete from role where id = " + id;
        logger.info("execute sql RoleDao.delete: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
        sqlString = "delete from role_privilege_mapping where role_no = '" + roleNo + "'";
        logger.info("execute sql RoleDao.delete: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    private static List<RoleDomain> packageObjects(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        List<RoleDomain> roleDomainList = new ArrayList<RoleDomain>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    RoleDomain roleDomain = new RoleDomain();
                    roleDomain.setId(resultSet.getInt("id"));
                    roleDomain.setNo(resultSet.getString("no"));
                    roleDomain.setName(resultSet.getString("name"));
                    roleDomainList.add(roleDomain);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return roleDomainList;
    }

    private static RoleDomain packageObject(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    RoleDomain roleDomain = new RoleDomain();
                    roleDomain.setId(resultSet.getInt("id"));
                    roleDomain.setNo(resultSet.getString("no"));
                    roleDomain.setName(resultSet.getString("name"));
                    return roleDomain;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
