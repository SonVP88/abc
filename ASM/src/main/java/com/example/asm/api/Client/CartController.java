package com.example.asm.api.Client;

import com.example.asm.entities.GioHang;
import com.example.asm.entities.GioHangChiTiet;
import com.example.asm.entities.TaiNghe;
import com.example.asm.entities.User;
import com.example.asm.service.IGioHangChiTietService;
import com.example.asm.service.IGioHangService;
import com.example.asm.service.ITaiNgheService;
import com.example.asm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    IUserService userService;

    @Autowired
    ITaiNgheService taiNgheService;

    @Autowired
    IGioHangService gioHangService;

    @Autowired
    IGioHangChiTietService gioHangChiTietService;

    @GetMapping("/cart/{id}")
    public String cartPage(@PathVariable(name = "id") Integer id, Model model) {
        Map<Integer, Integer> DSSP = new HashMap<>();
        List<TaiNghe> listTN = new ArrayList<>();
        User user = userService.getById(id);
        model.addAttribute("US", user);

        GioHang g = gioHangService.getByUser(user);
        if (g == null) {
            g = new GioHang();
            g.setUser(user);
            g = gioHangService.save(g);
            List<GioHangChiTiet> listGHCT = gioHangChiTietService.getByGh(g);
            for (GioHangChiTiet ghct : listGHCT) {
                listTN.add(ghct.getTaiNghe());
                DSSP.put(ghct.getTaiNghe().getTaiNgheId(), ghct.getSoLuong());
            }
            return "/Client/cart";
        }
        List<GioHangChiTiet> listGHCT = gioHangChiTietService.getByGh(g);
        for (GioHangChiTiet ghct : listGHCT) {
            listTN.add(ghct.getTaiNghe());
            DSSP.put(ghct.getTaiNghe().getTaiNgheId(), ghct.getSoLuong());
        }
        BigDecimal tongTien = gioHangService.tongTien(g);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("cart", listTN);
        model.addAttribute("DSSP", DSSP);
        return "/Client/cart";
    }

    @GetMapping("/cart/{id}/add-tai-nghe/{taiNgheId}")
    public String addToCart(@PathVariable(name = "id") Integer id, @PathVariable(name = "taiNgheId") Integer taiNgheId
            , Model model) {
        Map<Integer, Integer> DSSP = new HashMap<>();
        List<TaiNghe> listTN = new ArrayList<>();
        TaiNghe tn = taiNgheService.getById(taiNgheId);
        User user = userService.getById(id);
        model.addAttribute("US", user);
        GioHang g = gioHangService.getByUser(user);
        if (g == null) {
            g = new GioHang();
            g.setUser(user);
            g = gioHangService.save(g);
            List<GioHangChiTiet> listGHCT = gioHangChiTietService.getByGh(g);
            for (GioHangChiTiet ghct : listGHCT) {
                listTN.add(ghct.getTaiNghe());
                DSSP.put(ghct.getTaiNghe().getTaiNgheId(), ghct.getSoLuong());
            }
            GioHangChiTiet gioHangChiTiet = gioHangChiTietService.getByTaiNgheAndGh(tn, g);
            if (gioHangChiTiet == null) {
                gioHangChiTiet = new GioHangChiTiet();
                gioHangChiTiet.setTaiNghe(tn);
                gioHangChiTiet.setGh(g);
                gioHangChiTiet.setSoLuong(1);
            } else {
                gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + 1);
            }
            gioHangChiTiet = gioHangChiTietService.saveGHCT(gioHangChiTiet);
            return "redirect:/cart/{id}";
        }

        GioHangChiTiet gioHangChiTiet = gioHangChiTietService.getByTaiNgheAndGh(tn, g);
        if (gioHangChiTiet == null) {
            gioHangChiTiet = new GioHangChiTiet();
            gioHangChiTiet.setTaiNghe(tn);
            gioHangChiTiet.setGh(g);
            gioHangChiTiet.setSoLuong(1);
            gioHangChiTiet.setDonGia(tn.getDonGia());
        } else {
            gioHangChiTiet.setSoLuong(gioHangChiTiet.getSoLuong() + 1);
        }
        List<GioHangChiTiet> listGHCT = gioHangChiTietService.getByGh(g);
        for (GioHangChiTiet ghct : listGHCT) {
            listTN.add(ghct.getTaiNghe());
            DSSP.put(ghct.getTaiNghe().getTaiNgheId(), ghct.getSoLuong());
        }
        BigDecimal tongTien = gioHangService.tongTien(g);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("cart", listTN);
        model.addAttribute("DSSP", DSSP);
        gioHangChiTiet = gioHangChiTietService.saveGHCT(gioHangChiTiet);
        return "redirect:/cart/{id}";
    }

    @GetMapping("/cart/{id}/giamSoLuong/{taiNgheId}")
    public String giamSoLuong(@PathVariable(name = "id") Integer id, @PathVariable("taiNgheId") Integer taiNgheId
            , Model model) {
        User user = userService.getById(id);
        model.addAttribute("US", user);
        GioHang gh = gioHangService.getByUser(user);
        TaiNghe taiNghe = taiNgheService.getById(taiNgheId);
        GioHangChiTiet ghct = gioHangChiTietService.getByTaiNgheAndGh(taiNghe, gh);
        ghct.setSoLuong(ghct.getSoLuong() - 1);
        if (ghct.getSoLuong() == 0) {
            gioHangChiTietService.deleteGHCT(ghct);
            return "redirect:/cart/{id}";
        }
        ghct = gioHangChiTietService.saveGHCT(ghct);
        return "redirect:/cart/{id}";
    }

    @GetMapping("/cart/{id}/tangSoLuong/{taiNgheId}")
    public String tangSoLuong(@PathVariable(name = "id") Integer id, @PathVariable("taiNgheId") Integer taiNgheId
            , Model model) {
        User user = userService.getById(id);
        model.addAttribute("US", user);
        GioHang gh = gioHangService.getByUser(user);
        TaiNghe taiNghe = taiNgheService.getById(taiNgheId);
        GioHangChiTiet ghct = gioHangChiTietService.getByTaiNgheAndGh(taiNghe, gh);
        ghct.setSoLuong(ghct.getSoLuong() + 1);
        ghct = gioHangChiTietService.saveGHCT(ghct);
        return "redirect:/cart/{id}";
    }

    @GetMapping("/cart/{id}/xoaTaiNghe/{taiNgheId}")
    public String xoaTaiNghe(@PathVariable(name = "id") Integer id, @PathVariable("taiNgheId") Integer taiNgheId
            , Model model) {
        User user = userService.getById(id);
        model.addAttribute("US", user);
        GioHang gh = gioHangService.getByUser(user);
        TaiNghe taiNghe = taiNgheService.getById(taiNgheId);
        GioHangChiTiet ghct = gioHangChiTietService.getByTaiNgheAndGh(taiNghe, gh);
        gioHangChiTietService.deleteGHCT(ghct);
        return "redirect:/cart/{id}";
    }


}
