package com.learning.dao;

import com.learning.common.util.H2SqlUtil;
import com.learning.domain.ArticleDomain;
import com.learning.vo.ArticleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ArticleDao
 * Created by liuw on 17-4-28.
 */
public class ArticleDao {

    private static Logger logger = LoggerFactory.getLogger(AdminDao.class);

    public static List<Integer> fullPeriodList(String articleType, String articleLevel) {
        String sqlString = "select a.period, count(*) count from article a where a.type = '" + articleType + "' and a.level = '" + articleLevel + "' " +
                "group by a.period having count >= 3 order by a.period asc";
        logger.info("execute sql ArticleDao.fullPeriodList: " + sqlString);
        return fillPeriodList(sqlString);
    }

    public static List<Integer> periodList(String articleType, String articleLevel) {
        String sqlString = "select distinct(a.period) period from article a where a.type = '" + articleType + "' and a.level = '" + articleLevel + "' " +
                "order by a.period asc";
        logger.info("execute sql ArticleDao.periodList: " + sqlString);
        return fillPeriodList(sqlString);
    }

    private static List<Integer> fillPeriodList(String sqlString) {
        List<Integer> periodList = new ArrayList<Integer>();
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    periodList.add(resultSet.getInt("period"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return periodList;
    }

    public static ArticleDomain selectById(Integer id) {
        String sqlString = "select * from article where id = " + id;
        logger.info("execute sql ArticleDao.selectById: " + sqlString);
        return packageDomain(sqlString);
    }

    public static ArticleDomain selectByTitle(String type, String level, String title) {
        String formatter = "select * from article where type = '%s' and level = '%s' and title = '%s'";
        String sqlString = String.format(formatter, type, level, title);
        logger.info("execute sql ArticleDao.selectByTitle: " + sqlString);
        return packageDomain(sqlString);

    }

    public static ArticleDomain select(String type, String level, String period, Integer num) {
        String formatter = "select * from article where type = '%s' and level = '%s' and period = '%s' and num = %d";
        String sqlString = String.format(formatter, type, level, period, num);
        ArticleDomain articleDomain = packageDomain(sqlString);
        if (articleDomain != null) {
            articleDomain.setContents(Arrays.asList(articleDomain.getContent().split("\n")));
        }
        logger.info("execute sql ArticleDao.select: " + sqlString + ", result: " + articleDomain);
        return articleDomain;

    }

    public static Integer selectMaxNum(String type, String level, String period) {
        String formatter = "select max(num) as num from article where type = '%s' and level = '%s' and period = '%s'";
        String sqlString = String.format(formatter, type, level, period);
        logger.info("execute sql ArticleDao.selectMaxNum: " + sqlString);
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        try {
            if (resultSet != null && resultSet.next()) {
                return resultSet.getInt("num");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static List<ArticleDomain> articleList(String type, String level) {
        String formatter = "select * from article where type = '%s' and level = '%s' order by period asc, num asc";
        String sqlString = String.format(formatter, type, level);
        logger.info("execute sql ArticleDao.articleList: " + sqlString);
        return packageDomains(sqlString);
    }

    public static ArticleDomain detail(Integer id) {
        String sqlString = "select * from article where id = " + id;
        logger.info("execute sql ArticleDao.detail: " + sqlString);
        return packageDomain(sqlString);
    }

    public static void add(ArticleVo articleVo) {
        String formatter = "insert into article (type, level, period, num, title, content, " +
                "first_question, first_a, first_a_explain, first_b, first_b_explain, first_c, first_c_explain, first_d, first_d_explain, first_answer, " +
                "second_question, second_a, second_a_explain, second_b, second_b_explain, second_c, second_c_explain, second_d, second_d_explain, second_answer, " +
                "third_question, third_a, third_a_explain, third_b, third_b_explain, third_c, third_c_explain, third_d, third_d_explain, third_answer, " +
                "fourth_question, fourth_a, fourth_a_explain, fourth_b, fourth_b_explain, fourth_c, fourth_c_explain, fourth_d, fourth_d_explain, fourth_answer, " +
                "fifth_question, fifth_a, fifth_a_explain, fifth_b, fifth_b_explain, fifth_c, fifth_c_explain, fifth_d, fifth_d_explain, fifth_answer) " +
                "values ('%s', '%s', '%s', %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')";
        String sqlString = String.format(formatter, articleVo.getType(), articleVo.getLevel(), articleVo.getPeriod(), articleVo.getNum(),
                articleVo.getTitle(), articleVo.getContent(), articleVo.getFirstQuestion(), articleVo.getFirstA(), articleVo.getFirstAExplain(),
                articleVo.getFirstB(), articleVo.getFirstBExplain(), articleVo.getFirstC(), articleVo.getFirstCExplain(), articleVo.getFirstD(),
                articleVo.getFirstDExplain(), articleVo.getFirstAnswer(), articleVo.getSecondQuestion(), articleVo.getSecondA(), articleVo.getSecondAExplain(),
                articleVo.getSecondB(), articleVo.getSecondBExplain(), articleVo.getSecondC(), articleVo.getSecondCExplain(), articleVo.getSecondD(),
                articleVo.getSecondDExplain(), articleVo.getSecondAnswer(), articleVo.getThirdQuestion(), articleVo.getThirdA(), articleVo.getThirdAExplain(),
                articleVo.getThirdB(), articleVo.getThirdBExplain(), articleVo.getThirdC(), articleVo.getThirdCExplain(), articleVo.getThirdD(),
                articleVo.getThirdDExplain(), articleVo.getThirdAnswer(), articleVo.getFourthQuestion(), articleVo.getFourthA(), articleVo.getFourthAExplain(),
                articleVo.getFourthB(), articleVo.getFourthBExplain(), articleVo.getFourthC(), articleVo.getFourthCExplain(), articleVo.getFourthD(),
                articleVo.getFourthDExplain(), articleVo.getFourthAnswer(), articleVo.getFifthQuestion(), articleVo.getFifthA(), articleVo.getFifthAExplain(),
                articleVo.getFifthB(), articleVo.getFifthBExplain(), articleVo.getFifthC(), articleVo.getFifthCExplain(), articleVo.getFifthD(),
                articleVo.getFifthDExplain(), articleVo.getFifthAnswer());
        logger.info("execute sql ArticleDao.add: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void updateById(ArticleVo articleVo) {
        String formatter = "update article set title = '%s', content = '%s', " +
                "first_question = '%s', first_a = '%s', first_a_explain = '%s', first_b = '%s', first_b_explain = '%s', " +
                "first_c = '%s', first_c_explain = '%s', first_d = '%s', first_d_explain = '%s', first_answer = '%s'," +
                "second_question = '%s', second_a = '%s', second_a_explain = '%s', second_b = '%s', second_b_explain = '%s', " +
                "second_c = '%s', second_c_explain = '%s', second_d = '%s', second_d_explain = '%s', second_answer = '%s', " +
                "third_question = '%s', third_a = '%s', third_a_explain = '%s', third_b = '%s', third_b_explain = '%s', " +
                "third_c = '%s', third_c_explain = '%s', third_d = '%s', third_d_explain = '%s', third_answer = '%s', " +
                "fourth_question = '%s', fourth_a = '%s', fourth_a_explain = '%s', fourth_b = '%s', fourth_b_explain = '%s', " +
                "fourth_c = '%s', fourth_c_explain = '%s', fourth_d = '%s', fourth_d_explain = '%s', fourth_answer = '%s', " +
                "fifth_question = '%s', fifth_a = '%s', fifth_a_explain = '%s', fifth_b = '%s', fifth_b_explain = '%s', " +
                "fifth_c = '%s', fifth_c_explain = '%s', fifth_d = '%s', fifth_d_explain = '%s', fifth_answer = '%s' where id = %d";
        String sqlString = String.format(formatter, articleVo.getTitle(), articleVo.getContent(), articleVo.getFirstQuestion(), articleVo.getFirstA(), articleVo.getFirstAExplain(),
                articleVo.getFirstB(), articleVo.getFirstBExplain(), articleVo.getFirstC(), articleVo.getFirstCExplain(), articleVo.getFirstD(),
                articleVo.getFirstDExplain(), articleVo.getFirstAnswer(), articleVo.getSecondQuestion(), articleVo.getSecondA(), articleVo.getSecondAExplain(),
                articleVo.getSecondB(), articleVo.getSecondBExplain(), articleVo.getSecondC(), articleVo.getSecondCExplain(), articleVo.getSecondD(),
                articleVo.getSecondDExplain(), articleVo.getSecondAnswer(), articleVo.getThirdQuestion(), articleVo.getThirdA(), articleVo.getThirdAExplain(),
                articleVo.getThirdB(), articleVo.getThirdBExplain(), articleVo.getThirdC(), articleVo.getThirdCExplain(), articleVo.getThirdD(),
                articleVo.getThirdDExplain(), articleVo.getThirdAnswer(), articleVo.getFourthQuestion(), articleVo.getFourthA(), articleVo.getFourthAExplain(),
                articleVo.getFourthB(), articleVo.getFourthBExplain(), articleVo.getFourthC(), articleVo.getFourthCExplain(), articleVo.getFourthD(),
                articleVo.getFourthDExplain(), articleVo.getFourthAnswer(), articleVo.getFifthQuestion(), articleVo.getFifthA(), articleVo.getFifthAExplain(),
                articleVo.getFifthB(), articleVo.getFifthBExplain(), articleVo.getFifthC(), articleVo.getFifthCExplain(), articleVo.getFifthD(),
                articleVo.getFifthDExplain(), articleVo.getFifthAnswer(), articleVo.getId());
        logger.info("execute sql ArticleDao.updateById: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void updateNumById(Integer id, Integer num) {
        String sqlString = "update article set num = " + num + " where id = " + id;
        logger.info("execute sql ArticleDao.updateNumById: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void deleteById(Integer id) {
        String sqlString = "delete from article where id = " + id;
        logger.info("execute sql ArticleDao.deleteById: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    private static List<ArticleDomain> packageDomains(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        List<ArticleDomain> articleDomainList = new ArrayList<ArticleDomain>();
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    ArticleDomain articleDomain = new ArticleDomain();
                    articleDomain.setId(resultSet.getInt("id"));
                    articleDomain.setType(resultSet.getString("type"));
                    articleDomain.setLevel(resultSet.getString("level"));
                    articleDomain.setPeriod(resultSet.getString("period"));
                    articleDomain.setNum(resultSet.getInt("num"));
                    articleDomain.setTitle(resultSet.getString("title"));
                    articleDomain.setContent(resultSet.getString("content"));
                    articleDomain.setFirstQuestion(resultSet.getString("first_question"));
                    articleDomain.setFirstA(resultSet.getString("first_a"));
                    articleDomain.setFirstAExplain(resultSet.getString("first_a_explain"));
                    articleDomain.setFirstB(resultSet.getString("first_b"));
                    articleDomain.setFirstBExplain(resultSet.getString("first_b_explain"));
                    articleDomain.setFirstC(resultSet.getString("first_c"));
                    articleDomain.setFirstCExplain(resultSet.getString("first_c_explain"));
                    articleDomain.setFirstD(resultSet.getString("first_d"));
                    articleDomain.setFirstDExplain(resultSet.getString("first_d_explain"));
                    articleDomain.setFirstAnswer(resultSet.getString("first_answer"));
                    articleDomain.setSecondQuestion(resultSet.getString("second_question"));
                    articleDomain.setSecondA(resultSet.getString("second_a"));
                    articleDomain.setSecondAExplain(resultSet.getString("second_a_explain"));
                    articleDomain.setSecondB(resultSet.getString("second_b"));
                    articleDomain.setSecondBExplain(resultSet.getString("second_b_explain"));
                    articleDomain.setSecondC(resultSet.getString("second_c"));
                    articleDomain.setSecondCExplain(resultSet.getString("second_c_explain"));
                    articleDomain.setSecondD(resultSet.getString("second_d"));
                    articleDomain.setSecondDExplain(resultSet.getString("second_d_explain"));
                    articleDomain.setSecondAnswer(resultSet.getString("second_answer"));
                    articleDomain.setThirdQuestion(resultSet.getString("third_question"));
                    articleDomain.setThirdA(resultSet.getString("third_a"));
                    articleDomain.setThirdAExplain(resultSet.getString("third_a_explain"));
                    articleDomain.setThirdB(resultSet.getString("third_b"));
                    articleDomain.setThirdBExplain(resultSet.getString("third_b_explain"));
                    articleDomain.setThirdC(resultSet.getString("third_c"));
                    articleDomain.setThirdCExplain(resultSet.getString("third_c_explain"));
                    articleDomain.setThirdD(resultSet.getString("third_d"));
                    articleDomain.setThirdDExplain(resultSet.getString("third_d_explain"));
                    articleDomain.setThirdAnswer(resultSet.getString("third_answer"));
                    articleDomain.setFourthQuestion(resultSet.getString("fourth_question"));
                    articleDomain.setFourthA(resultSet.getString("fourth_a"));
                    articleDomain.setFourthAExplain(resultSet.getString("fourth_a_explain"));
                    articleDomain.setFourthB(resultSet.getString("fourth_b"));
                    articleDomain.setFourthBExplain(resultSet.getString("fourth_b_explain"));
                    articleDomain.setFourthC(resultSet.getString("fourth_c"));
                    articleDomain.setFourthCExplain(resultSet.getString("fourth_c_explain"));
                    articleDomain.setFourthD(resultSet.getString("fourth_d"));
                    articleDomain.setFourthDExplain(resultSet.getString("fourth_d_explain"));
                    articleDomain.setFourthAnswer(resultSet.getString("fourth_answer"));
                    articleDomain.setFifthQuestion(resultSet.getString("fifth_question"));
                    articleDomain.setFifthA(resultSet.getString("fifth_a"));
                    articleDomain.setFifthAExplain(resultSet.getString("fifth_a_explain"));
                    articleDomain.setFifthB(resultSet.getString("fifth_b"));
                    articleDomain.setFifthBExplain(resultSet.getString("fifth_b_explain"));
                    articleDomain.setFifthC(resultSet.getString("fifth_c"));
                    articleDomain.setFifthCExplain(resultSet.getString("fifth_c_explain"));
                    articleDomain.setFifthD(resultSet.getString("fifth_d"));
                    articleDomain.setFifthDExplain(resultSet.getString("fifth_d_explain"));
                    articleDomain.setFifthAnswer(resultSet.getString("fifth_answer"));
                    articleDomainList.add(articleDomain);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return articleDomainList;
    }

    private static ArticleDomain packageDomain(String sqlString) {
        ResultSet resultSet = H2SqlUtil.querySql(sqlString);
        if (resultSet != null) {
            try {
                if (resultSet.next()) {
                    ArticleDomain articleDomain = new ArticleDomain();
                    articleDomain.setId(resultSet.getInt("id"));
                    articleDomain.setType(resultSet.getString("type"));
                    articleDomain.setLevel(resultSet.getString("level"));
                    articleDomain.setPeriod(resultSet.getString("period"));
                    articleDomain.setNum(resultSet.getInt("num"));
                    articleDomain.setTitle(resultSet.getString("title"));
                    articleDomain.setContent(resultSet.getString("content"));
                    articleDomain.setFirstQuestion(resultSet.getString("first_question"));
                    articleDomain.setFirstA(resultSet.getString("first_a"));
                    articleDomain.setFirstAExplain(resultSet.getString("first_a_explain"));
                    articleDomain.setFirstB(resultSet.getString("first_b"));
                    articleDomain.setFirstBExplain(resultSet.getString("first_b_explain"));
                    articleDomain.setFirstC(resultSet.getString("first_c"));
                    articleDomain.setFirstCExplain(resultSet.getString("first_c_explain"));
                    articleDomain.setFirstD(resultSet.getString("first_d"));
                    articleDomain.setFirstDExplain(resultSet.getString("first_d_explain"));
                    articleDomain.setFirstAnswer(resultSet.getString("first_answer"));
                    articleDomain.setSecondQuestion(resultSet.getString("second_question"));
                    articleDomain.setSecondA(resultSet.getString("second_a"));
                    articleDomain.setSecondAExplain(resultSet.getString("second_a_explain"));
                    articleDomain.setSecondB(resultSet.getString("second_b"));
                    articleDomain.setSecondBExplain(resultSet.getString("second_b_explain"));
                    articleDomain.setSecondC(resultSet.getString("second_c"));
                    articleDomain.setSecondCExplain(resultSet.getString("second_c_explain"));
                    articleDomain.setSecondD(resultSet.getString("second_d"));
                    articleDomain.setSecondDExplain(resultSet.getString("second_d_explain"));
                    articleDomain.setSecondAnswer(resultSet.getString("second_answer"));
                    articleDomain.setThirdQuestion(resultSet.getString("third_question"));
                    articleDomain.setThirdA(resultSet.getString("third_a"));
                    articleDomain.setThirdAExplain(resultSet.getString("third_a_explain"));
                    articleDomain.setThirdB(resultSet.getString("third_b"));
                    articleDomain.setThirdBExplain(resultSet.getString("third_b_explain"));
                    articleDomain.setThirdC(resultSet.getString("third_c"));
                    articleDomain.setThirdCExplain(resultSet.getString("third_c_explain"));
                    articleDomain.setThirdD(resultSet.getString("third_d"));
                    articleDomain.setThirdDExplain(resultSet.getString("third_d_explain"));
                    articleDomain.setThirdAnswer(resultSet.getString("third_answer"));
                    articleDomain.setFourthQuestion(resultSet.getString("fourth_question"));
                    articleDomain.setFourthA(resultSet.getString("fourth_a"));
                    articleDomain.setFourthAExplain(resultSet.getString("fourth_a_explain"));
                    articleDomain.setFourthB(resultSet.getString("fourth_b"));
                    articleDomain.setFourthBExplain(resultSet.getString("fourth_b_explain"));
                    articleDomain.setFourthC(resultSet.getString("fourth_c"));
                    articleDomain.setFourthCExplain(resultSet.getString("fourth_c_explain"));
                    articleDomain.setFourthD(resultSet.getString("fourth_d"));
                    articleDomain.setFourthDExplain(resultSet.getString("fourth_d_explain"));
                    articleDomain.setFourthAnswer(resultSet.getString("fourth_answer"));
                    articleDomain.setFifthQuestion(resultSet.getString("fifth_question"));
                    articleDomain.setFifthA(resultSet.getString("fifth_a"));
                    articleDomain.setFifthAExplain(resultSet.getString("fifth_a_explain"));
                    articleDomain.setFifthB(resultSet.getString("fifth_b"));
                    articleDomain.setFifthBExplain(resultSet.getString("fifth_b_explain"));
                    articleDomain.setFifthC(resultSet.getString("fifth_c"));
                    articleDomain.setFifthCExplain(resultSet.getString("fifth_c_explain"));
                    articleDomain.setFifthD(resultSet.getString("fifth_d"));
                    articleDomain.setFifthDExplain(resultSet.getString("fifth_d_explain"));
                    articleDomain.setFifthAnswer(resultSet.getString("fifth_answer"));
                    return articleDomain;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
