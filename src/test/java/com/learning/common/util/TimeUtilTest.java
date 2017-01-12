package com.learning.common.util;

import org.junit.Test;

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
}
