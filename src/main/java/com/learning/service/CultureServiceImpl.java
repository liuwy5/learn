package com.learning.service;

import com.learning.bean.Resp;
import com.learning.common.enums.RespStatusEnum;
import com.learning.common.util.TimeUtil;
import com.learning.dao.CultureDao;
import com.learning.vo.CultureVo;

import java.util.List;

/**
 *
 * Created by liuw on 17-1-17.
 */
public class CultureServiceImpl {
    public Resp add(CultureVo cultureVo) {
        String time = TimeUtil.getDateNormalNow();
        cultureVo.setCreatedAt(time);
        CultureDao.add(cultureVo);
        return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
    }

    public List<CultureVo> selectAllCulture() {
        return CultureDao.selectAllCulture();
    }

    public CultureVo selectById(Integer id) {
        return CultureDao.selectById(id);
    }

    public Resp deleteById(Integer id) {
        CultureDao.deleteById(id);
        return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
    }

    public Resp updateById(CultureVo cultureVo) {
        CultureDao.updateById(cultureVo);
        return new Resp(RespStatusEnum.RESP_SUCCESS.getCode(), RespStatusEnum.RESP_SUCCESS.getMean());
    }
}
