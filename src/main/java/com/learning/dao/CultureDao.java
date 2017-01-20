package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.vo.CultureVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by liuw on 17-1-6.
 */
public class CultureDao {

    private static Logger logger = LoggerFactory.getLogger(AdminDao.class);

    public static void add(CultureVo cultureVo) {
        String formatter = "insert into culture (title, content, created_at) values ('%s', '%s', '%s')";
        String sqlString = String.format(formatter, cultureVo.getTitle(), cultureVo.getContent(), cultureVo.getCreatedAt());
        logger.info("execute sql CultureDao.add: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static List<CultureVo> selectAllCulture() {
        String sqlString = "select * from culture order by id desc";
        logger.info("execute sql CultureDao.selectAllCulture: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        List<CultureVo> cultureVoList = new ArrayList<CultureVo>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    CultureVo cultureVo = new CultureVo();
                    cultureVo.setId(resultSet.getInt("id"));
                    cultureVo.setTitle(resultSet.getString("title"));
                    cultureVo.setContent(resultSet.getString("content"));
                    cultureVo.setCreatedAt(resultSet.getString("created_at"));
                    cultureVoList.add(cultureVo);
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return cultureVoList;
    }

    public static CultureVo selectById(Integer id) {
        String sqlString = "select * from culture where id = " + id;
        logger.info("execute sql CultureDao.selectById: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        try {
            if (resultSet != null && resultSet.next()) {
                CultureVo cultureVo = new CultureVo();
                cultureVo.setId(resultSet.getInt("id"));
                cultureVo.setTitle(resultSet.getString("title"));
                cultureVo.setContent(resultSet.getString("content"));
                cultureVo.setCreatedAt(resultSet.getString("created_at"));
                return cultureVo;
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static void deleteById(Integer id) {
        String sqlString = "delete from culture where id = " + id;
        logger.info("execute sql CultureDao.selectById: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void updateById(CultureVo cultureVo) {
        String formatter = "update culture set title = '%s', content = '%s' where id = %d";
        String sqlString = String.format(formatter, cultureVo.getTitle(), cultureVo.getContent(), cultureVo.getId());
        logger.info("execute sql CultureDao.updateById: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }
}
