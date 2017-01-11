package com.learning.controller.api.customer;

import com.learning.bean.Resp;
import com.learning.service.InterestServiceImpl;
import com.learning.vo.InterestVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Created by liuw on 17-1-9.
 */
@RestController
@RequestMapping("/interest")
public class InterestController {
    private InterestServiceImpl interestService = new InterestServiceImpl();

    @RequestMapping("/add")
    public Resp addChat(InterestVo interestVo) {
        return interestService.addChat(interestVo);
    }
}
