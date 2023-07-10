package com.example.asm.api.Client;

import com.example.asm.entities.Role;
import com.example.asm.entities.TaiNghe;
import com.example.asm.entities.User;
import com.example.asm.service.IRoleService;
import com.example.asm.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class UserController {

    @Autowired
    IRoleService roleService;

    @Autowired
    IUserService userService;

    @GetMapping("/sign-up-view")
    public String viewSignUp(){
        return "/Auth/sign-up";
    }

    @GetMapping("/view-update-pass/{id}")
    public String viewUpdatePass(Model model,
                                 @PathVariable(name = "id") Integer id){
        User user = userService.getById(id);
        model.addAttribute("US", user);
        return "/Auth/update-pass";
    }

    @GetMapping("/edit-user-view/{id}")
    public String viewEditUser(Model model, @PathVariable(name = "id") Integer id){
        User user = userService.getById(id);
        model.addAttribute("US", user);
        return "/Auth/edit-user";
    }

    @PostMapping("/sign-up-user")
    public String dangKiUser(Model model, HttpServletRequest request){
        String ma = request.getParameter("ma");
        String ten = request.getParameter("ten");
        String gioiTinh = request.getParameter("gioiTinh");
        String ngaySinh = request.getParameter("ngaySinh");
        String diaChi = request.getParameter("diaChi");
        String sdt = request.getParameter("sdt");
        String email = request.getParameter("email");
        String matKhau = request.getParameter("matKhau");

        Role role = roleService.getById(1);//1:User, 2:Admin

        User u = User.builder()
                .ma(ma)
                .ten(ten)
                .gioiTinh(gioiTinh)
                .ngaySinh(Date.valueOf(ngaySinh))
                .sdt(sdt)
                .diaChi(diaChi)
                .email(email)
                .matKhau(matKhau)
                .trangThai(0)
                .role(role)
                .build();

        userService.addUser(u);
        return "redirect:/loginView";
    }

    @PostMapping("/update-pass/{id}")
    public String updatePass(Model model,HttpServletRequest request,
                             @Validated User u,BindingResult result, @PathVariable(name = "id") Integer id){
        User user = userService.getById(id);
        model.addAttribute("US", user);
        String passht = request.getParameter("matKhauHT");
        if(!passht.equals(user.getMatKhau())){
            model.addAttribute("checkPass", 1);
            return "/Auth/update-pass";
        }
        if (result.hasErrors()) {
            u.setId(id);
            return "redirect:/view-update-pass/{id}";
        }
        u.setMa(user.getMa());
        u.setTen(user.getTen());
        u.setDiaChi(user.getDiaChi());
        u.setEmail(user.getEmail());
        u.setGioiTinh(user.getGioiTinh());
        u.setRole(user.getRole());
        u.setNgaySinh(user.getNgaySinh());
        u.setTrangThai(user.getTrangThai());
        u.setSdt(user.getSdt());
        userService.updateUser(u);
        return "redirect:/client/{id}";
    }

    @PostMapping("/update-user/{id}")
    public String updateUser(Model model,@Validated User u
            ,@PathVariable(name = "id") Integer id, BindingResult result){

        User user = userService.getById(id);
        model.addAttribute("US", user);
        if (result.hasErrors()) {
            u.setId(id);
            return "redirect:/edit-user-view/{id}";
        }
        u.setMatKhau(user.getMatKhau());
        u.setRole(user.getRole());
        u.setTrangThai(user.getTrangThai());
        userService.updateUser(u);
        return "redirect:/client/{id}";
    }
}
