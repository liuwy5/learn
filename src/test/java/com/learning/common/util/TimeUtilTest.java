package com.learning.common.util;

import org.junit.Test;

import java.math.BigDecimal;

/**
 *
 * Created by liuw on 17-1-12.
 */
public class TimeUtilTest {

    @Test
    public void getDateNow() {
        System.out.println(TimeUtil.getDateNow().toString());
    }

    @Test
    public void getDateNormalNowTest() {
        System.out.println(TimeUtil.getDateNormalNow());
    }

    @Test
    public void getDateTest() {
        System.out.println(TimeUtil.getDate("yyyyMMdd_HHmmss_SSS"));
    }

    @Test
    public void de() {
        BigDecimal s = new BigDecimal("3.456723");
        BigDecimal s1 = new BigDecimal("3.356723");

        int result = s.compareTo(s1);
        System.out.println(result);
        result = s.compareTo(s1);
        System.out.println(result);
        result = s1.compareTo(s);
        System.out.println(result);
    }
}
