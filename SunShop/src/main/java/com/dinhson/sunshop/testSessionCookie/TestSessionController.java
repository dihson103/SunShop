package com.dinhson.sunshop.testSessionCookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TestSessionController {

    @GetMapping("testSession")
    public String getSession(@SessionAttribute(name = "name", required = false) String name,
                             Model model) {
        name = "thuy linh";
        model.addAttribute("name", name);
        return "testCookie";
    }

//    @ModelAttribute("name")
//    public String name() {
//        return "dinh son";
//    }

}
