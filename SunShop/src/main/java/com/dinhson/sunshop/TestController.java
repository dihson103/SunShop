package com.dinhson.sunshop;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TestController{

    @GetMapping("hello")
    public String hello(){
        return "test";
    }

    @GetMapping("change")
    public String change(@SessionAttribute(name = "name") String name,
                         Model model){
        name = "thuy linh";
        model.addAttribute("name", name);
        return "test";
    }

    @GetMapping("testCookie")
    public String testCookie(@CookieValue(name = "testCookie", defaultValue = "1*dinhson_23_hanoi") String cookie,
                             Model model){
        String[] cookies = cookie.split("#");
        String ps = Arrays.stream(cookies).filter(s -> s.startsWith(11 + "*")).toString();

        String[] persons = cookie.split("\\+");
        List<Person> list = new ArrayList<>();
        for (String str: persons) {
            String[] arr = str.split("_");
            int age = Integer.parseInt(arr[1]);
            Person person = new Person();
            person.setName(arr[0]);
            person.setAge(age);
            person.setAddress(arr[2]);
            list.add(person);
        }
        model.addAttribute("list", list);
        return "testCookie";
    }

    @GetMapping("changeCookie")
    public String testChangeCookie(HttpServletResponse response){
        String names = "1*dinhson_23_hanoi+thuylinh_22_hanoi+ngocmai_22_hanoi#11*dinhson_20_hanoi+thuylinh_22_hanoi+ngocmai_22_hanoi";
        Cookie cookie = new Cookie("testCookie", names);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        return "redirect:testCookie";
    }


    @ModelAttribute("name")
    public String name(){
        return "dinh son";
    }
}
