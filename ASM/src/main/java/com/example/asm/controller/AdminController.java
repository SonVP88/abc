package com.example.asm.controller;

import com.example.asm.entities.TaiNghe;
import com.example.asm.entities.User;
import com.example.asm.service.ITaiNgheService;
import com.example.asm.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private Page<TaiNghe> pageTaiNghe;

    @Autowired
    IUserService userService;

    @Autowired
    ITaiNgheService taiNgheService;

    @GetMapping
    public String adminPage(Model model) {
        return "Admin/home-admin";
    }

    @GetMapping("/quan-ly-tai-nghe")
    public String viewAll(Model model,
                          HttpServletRequest request,
                          @RequestParam(defaultValue = "1") int page) {
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
        String email = request.getParameter("userName");
        String pass = request.getParameter("passWord");
        User user = userService.getByEmailAndPass(email, pass);
        model.addAttribute("US", user);

        pageTaiNghe = taiNgheService.getAll(pageable);
        model.addAttribute("page", pageTaiNghe);
        return "/Admin/quan-ly-tai-nghe";
    }

    @GetMapping("/thong-ke")
    public String viewTT() {
        return "/Admin/thong-ke";
    }

}
