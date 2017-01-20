package com.learning.controller.ui.admin;

import com.learning.service.CultureServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * Created by lw on 16-12-17.
 */
@Controller
@RequestMapping("admin/culture")
public class CultureController {
    private CultureServiceImpl cultureService = new CultureServiceImpl();

    @RequestMapping("")
    public String cultuerList(Model model) {
        model.addAttribute("cultureList", cultureService.selectAllCulture());
        return "admin/culture";
    }
}
