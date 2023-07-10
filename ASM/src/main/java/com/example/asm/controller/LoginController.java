package com.example.asm.controller;

import com.example.asm.entities.TaiNghe;
import com.example.asm.entities.User;
import com.example.asm.repository.IRoleRepository;
import com.example.asm.service.ITaiNgheService;
import com.example.asm.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private Page<TaiNghe> pageTaiNghe;

    @Autowired
    ITaiNgheService taiNgheService;

    @Autowired
    @Qualifier("UserService")
    IUserService userService;

    @Autowired
    IRoleRepository iRoleRepository;

    @GetMapping("/loginView")
    public String clientLogin() {
        return "Auth/login";
    }


    @GetMapping("/home")
    public String home(Model model, @RequestParam(defaultValue = "1") int page) {
        pageTaiNghe =getListTN(model,page);
        model.addAttribute("page", pageTaiNghe);
        return "/Auth/index";
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletRequest request
                        ,@RequestParam(defaultValue = "1") int page) {
        String email = request.getParameter("userName");
        String pass = request.getParameter("passWord");
        User user = userService.getByEmailAndPass(email, pass);
        if (user == null) {
            model.addAttribute("checkLogin", 1);
            return "/Auth/login";
        } else {
            if (user.getRole().getMa().equals("2")) {
                model.addAttribute("US", user);
                return "/Admin/home-admin";
            } else if (user.getRole().getMa().equals("1")) {
                model.addAttribute("US", user);
                pageTaiNghe =getListTN(model,page);
                model.addAttribute("page", pageTaiNghe);
                return "/Client/home-client";
            }
        }
        return "/Auth/login";
    }

    public Page<TaiNghe> getListTN(Model model, @RequestParam(defaultValue = "1") int page){
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
        pageTaiNghe = taiNgheService.getAll(pageable);
        model.addAttribute("page", pageTaiNghe);
        return pageTaiNghe;
    }

}
