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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client/{id}")
public class ClientController {

    private Page<TaiNghe> pageTaiNghe;

    @Autowired
    IUserService userService;

    @Autowired
    ITaiNgheService taiNgheService;

    @GetMapping
    public String ClientPage(Model model,@PathVariable(name = "id") Integer id,
                             @RequestParam(defaultValue = "1") int page) {

        User u = userService.getById(id);
        model.addAttribute("US", u);
        if (page < 1) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 10);
        pageTaiNghe = taiNgheService.getAll(pageable);
        model.addAttribute("page", pageTaiNghe);
        return "/Client/home-client";
    }
}
