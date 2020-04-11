package com.example.springbootdemos.web.form;

import com.example.springbootdemos.web.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/demo/web")
public class UserController {

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user",new User());
        return "web/login";
    }

    @PostMapping("/login")
    public String validate(@ModelAttribute User user) {
        if(validation(user)) {
            return "web/dashboard";
        }
        else {
            return "web/login";
        }

    }

    private boolean validation(User user) {
        //validation over user
        return true;
    }

}
