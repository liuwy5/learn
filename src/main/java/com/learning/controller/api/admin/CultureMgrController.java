package com.learning.controller.api.admin;

import com.learning.bean.Resp;
import com.learning.service.CultureServiceImpl;
import com.learning.vo.CultureVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * Created by liuw on 17-1-17.
 */
@RequestMapping("/culture")
@RestController
public class CultureMgrController {
    private CultureServiceImpl cultureService = new CultureServiceImpl();

    @RequestMapping("/add")
    public Resp add(CultureVo cultureVo) {
        return cultureService.add(cultureVo);
    }

    @RequestMapping("/delete")
    public Resp delete(Integer id) {
        return cultureService.deleteById(id);
    }

    @RequestMapping("/list")
    public List<CultureVo> selectAllCulture() {
        return cultureService.selectAllCulture();
    }

    @RequestMapping("/detail")
    public CultureVo selectById(Integer id) {
        return cultureService.selectById(id);
    }

    @RequestMapping("/update")
    public Resp updateById(CultureVo cultureVo) {
        return cultureService.updateById(cultureVo);
    }
}
