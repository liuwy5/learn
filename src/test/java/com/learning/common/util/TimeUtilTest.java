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
        BigDecimal result = s.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(result);
        s = new BigDecimal("3.446723");
        result = s.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(result);
    }
}
