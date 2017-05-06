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
                "first_question, first_a, first_b, first_c, first_d, first_answer, first_explain, " +
                "second_question, second_a, second_b, second_c, second_d, second_answer, second_explain, " +
                "third_question, third_a, third_b, third_c, third_d, third_answer, third_explain, " +
                "fourth_question, fourth_a, fourth_b, fourth_c, fourth_d, fourth_answer, fourth_explain, " +
                "fifth_question, fifth_a, fifth_b, fifth_c, fifth_d, fifth_answer, fifth_explain) " +
                "values ('%s', '%s', '%s', %d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                "'%s', '%s', '%s', '%s', '%s')";
        String sqlString = String.format(formatter, articleVo.getType(), articleVo.getLevel(), articleVo.getPeriod(), articleVo.getNum(),
                articleVo.getTitle(), articleVo.getContent(), articleVo.getFirstQuestion(), articleVo.getFirstA(),
                articleVo.getFirstB(), articleVo.getFirstC(), articleVo.getFirstD(),
                articleVo.getFirstAnswer(), articleVo.getFirstExplain(), articleVo.getSecondQuestion(), articleVo.getSecondA(),
                articleVo.getSecondB(), articleVo.getSecondC(), articleVo.getSecondD(),
                articleVo.getSecondAnswer(), articleVo.getSecondExplain(), articleVo.getThirdQuestion(), articleVo.getThirdA(),
                articleVo.getThirdB(), articleVo.getThirdC(), articleVo.getThirdD(),
                articleVo.getThirdAnswer(), articleVo.getThirdExplain(), articleVo.getFourthQuestion(), articleVo.getFourthA(),
                articleVo.getFourthB(), articleVo.getFourthC(), articleVo.getFourthD(),
                articleVo.getFourthAnswer(), articleVo.getFourthExplain(), articleVo.getFifthQuestion(), articleVo.getFifthA(),
                articleVo.getFifthB(), articleVo.getFifthC(), articleVo.getFifthD(),
                articleVo.getFifthAnswer(), articleVo.getFifthExplain());
        logger.info("execute sql ArticleDao.add: " + sqlString);
        H2SqlUtil.updateSql(sqlString);
    }

    public static void updateById(ArticleVo articleVo) {
        String formatter = "update article set title = '%s', content = '%s', " +
                "first_question = '%s', first_a = '%s', first_b = '%s',  " +
                "first_c = '%s', first_d = '%s', first_answer = '%s', first_explain = '%s', " +
                "second_question = '%s', second_a = '%s', second_b = '%s', " +
                "second_c = '%s', second_d = '%s', second_answer = '%s', second_explain = '%s', " +
                "third_question = '%s', third_a = '%s', third_b = '%s', " +
                "third_c = '%s', third_d = '%s', third_answer = '%s', third_explain = '%s', " +
                "fourth_question = '%s', fourth_a = '%s', fourth_b = '%s', " +
                "fourth_c = '%s', fourth_d = '%s', fourth_answer = '%s', fourth_explain = '%s', " +
                "fifth_question = '%s', fifth_a = '%s', fifth_b = '%s', " +
                "fifth_c = '%s', fifth_d = '%s', fifth_answer = '%s', fifth_explain = '%s' where id = %d";
        String sqlString = String.format(formatter, articleVo.getTitle(), articleVo.getContent(), articleVo.getFirstQuestion(), articleVo.getFirstA(),
                articleVo.getFirstB(),articleVo.getFirstC(), articleVo.getFirstD(),
                articleVo.getFirstAnswer(), articleVo.getFirstExplain(), articleVo.getSecondQuestion(), articleVo.getSecondA(),
                articleVo.getSecondB(), articleVo.getSecondC(), articleVo.getSecondD(),
                articleVo.getSecondAnswer(), articleVo.getSecondExplain(),articleVo.getThirdQuestion(), articleVo.getThirdA(),
                articleVo.getThirdB(), articleVo.getThirdC(), articleVo.getThirdD(),
                articleVo.getThirdAnswer(), articleVo.getThirdExplain(),articleVo.getFourthQuestion(), articleVo.getFourthA(),
                articleVo.getFourthB(), articleVo.getFourthC(), articleVo.getFourthD(),
                articleVo.getFourthAnswer(), articleVo.getFourthExplain(), articleVo.getFifthQuestion(), articleVo.getFifthA(),
                articleVo.getFifthB(), articleVo.getFifthC(), articleVo.getFifthD(),
                articleVo.getFifthAnswer(),  articleVo.getFifthExplain(), articleVo.getId());
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
                    articleDomain.setFirstB(resultSet.getString("first_b"));
                    articleDomain.setFirstC(resultSet.getString("first_c"));
                    articleDomain.setFirstD(resultSet.getString("first_d"));
                    articleDomain.setFirstAnswer(resultSet.getString("first_answer"));
                    articleDomain.setFirstExplain(resultSet.getString("first_explain"));
                    articleDomain.setSecondQuestion(resultSet.getString("second_question"));
                    articleDomain.setSecondA(resultSet.getString("second_a"));
                    articleDomain.setSecondB(resultSet.getString("second_b"));
                    articleDomain.setSecondC(resultSet.getString("second_c"));
                    articleDomain.setSecondD(resultSet.getString("second_d"));
                    articleDomain.setSecondAnswer(resultSet.getString("second_answer"));
                    articleDomain.setSecondExplain(resultSet.getString("second_explain"));
                    articleDomain.setThirdQuestion(resultSet.getString("third_question"));
                    articleDomain.setThirdA(resultSet.getString("third_a"));
                    articleDomain.setThirdB(resultSet.getString("third_b"));
                    articleDomain.setThirdC(resultSet.getString("third_c"));
                    articleDomain.setThirdD(resultSet.getString("third_d"));
                    articleDomain.setThirdAnswer(resultSet.getString("third_answer"));
                    articleDomain.setThirdExplain(resultSet.getString("third_explain"));
                    articleDomain.setFourthQuestion(resultSet.getString("fourth_question"));
                    articleDomain.setFourthA(resultSet.getString("fourth_a"));
                    articleDomain.setFourthB(resultSet.getString("fourth_b"));
                    articleDomain.setFourthC(resultSet.getString("fourth_c"));
                    articleDomain.setFourthD(resultSet.getString("fourth_d"));
                    articleDomain.setFourthAnswer(resultSet.getString("fourth_answer"));
                    articleDomain.setFourthExplain(resultSet.getString("fourth_explain"));
                    articleDomain.setFifthQuestion(resultSet.getString("fifth_question"));
                    articleDomain.setFifthA(resultSet.getString("fifth_a"));
                    articleDomain.setFifthB(resultSet.getString("fifth_b"));
                    articleDomain.setFifthC(resultSet.getString("fifth_c"));
                    articleDomain.setFifthD(resultSet.getString("fifth_d"));
                    articleDomain.setFifthAnswer(resultSet.getString("fifth_answer"));
                    articleDomain.setFifthExplain(resultSet.getString("fifth_explain"));
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
                    articleDomain.setFirstB(resultSet.getString("first_b"));
                    articleDomain.setFirstC(resultSet.getString("first_c"));
                    articleDomain.setFirstD(resultSet.getString("first_d"));
                    articleDomain.setFirstAnswer(resultSet.getString("first_answer"));
                    articleDomain.setFirstExplain(resultSet.getString("first_explain"));
                    articleDomain.setSecondQuestion(resultSet.getString("second_question"));
                    articleDomain.setSecondA(resultSet.getString("second_a"));
                    articleDomain.setSecondB(resultSet.getString("second_b"));
                    articleDomain.setSecondC(resultSet.getString("second_c"));
                    articleDomain.setSecondD(resultSet.getString("second_d"));
                    articleDomain.setSecondAnswer(resultSet.getString("second_answer"));
                    articleDomain.setSecondExplain(resultSet.getString("second_explain"));
                    articleDomain.setThirdQuestion(resultSet.getString("third_question"));
                    articleDomain.setThirdA(resultSet.getString("third_a"));
                    articleDomain.setThirdB(resultSet.getString("third_b"));
                    articleDomain.setThirdC(resultSet.getString("third_c"));
                    articleDomain.setThirdD(resultSet.getString("third_d"));
                    articleDomain.setThirdAnswer(resultSet.getString("third_answer"));
                    articleDomain.setThirdExplain(resultSet.getString("third_explain"));
                    articleDomain.setFourthQuestion(resultSet.getString("fourth_question"));
                    articleDomain.setFourthA(resultSet.getString("fourth_a"));
                    articleDomain.setFourthB(resultSet.getString("fourth_b"));
                    articleDomain.setFourthC(resultSet.getString("fourth_c"));
                    articleDomain.setFourthD(resultSet.getString("fourth_d"));
                    articleDomain.setFourthAnswer(resultSet.getString("fourth_answer"));
                    articleDomain.setFourthExplain(resultSet.getString("fourth_explain"));
                    articleDomain.setFifthQuestion(resultSet.getString("fifth_question"));
                    articleDomain.setFifthA(resultSet.getString("fifth_a"));
                    articleDomain.setFifthB(resultSet.getString("fifth_b"));
                    articleDomain.setFifthC(resultSet.getString("fifth_c"));
                    articleDomain.setFifthD(resultSet.getString("fifth_d"));
                    articleDomain.setFifthAnswer(resultSet.getString("fifth_answer"));
                    articleDomain.setFifthExplain(resultSet.getString("fifth_explain"));
                    return articleDomain;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
