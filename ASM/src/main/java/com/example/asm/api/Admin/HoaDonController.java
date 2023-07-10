package com.example.asm.api.Admin;

import com.example.asm.entities.GioHang;
import com.example.asm.entities.GioHangChiTiet;
import com.example.asm.entities.HoaDon;
import com.example.asm.entities.HoaDonChiTiet;
import com.example.asm.entities.TaiNghe;
import com.example.asm.entities.User;
import com.example.asm.service.IGioHangChiTietService;
import com.example.asm.service.IGioHangService;
import com.example.asm.service.IHoaDonChiTietService;
import com.example.asm.service.IHoaDonService;
import com.example.asm.service.ITaiNgheService;
import com.example.asm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {

    @Autowired
    IGioHangService gioHangService;

    @Autowired
    IGioHangChiTietService gioHangChiTietService;

    @Autowired
    IHoaDonService hoaDonService;

    @Autowired
    IUserService userService;

    @Autowired
    ITaiNgheService taiNgheService;

    @Autowired
    IHoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/{id}")
    public String hoaDonView(@PathVariable(name = "id") Integer id, Model model) {
        Map<Integer, Integer> DSSP = new HashMap<>();
        List<TaiNghe> listTN = new ArrayList<>();
        User user = userService.getById(id);
        model.addAttribute("US", user);
        List<HoaDon> listhd = hoaDonService.getAllByUser(user);

        for (HoaDon hd : listhd) {
            List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getByHd(hd);
            for (HoaDonChiTiet hdct : listHDCT) {
                listTN.add(hdct.getTaiNghe());
                DSSP.put(hdct.getTaiNghe().getTaiNgheId(), hdct.getSoLuong());
            }
            BigDecimal tongTien = hoaDonService.tongTien(hd);
            model.addAttribute("listHD", listhd);
            model.addAttribute("tongTien", tongTien);
            model.addAttribute("hoaDon", listTN);
            model.addAttribute("DSSP", DSSP);
        }
        return "/Client/hoa-don";
    }

    @GetMapping("/detailCT/{id}")
    public String hoaDonchitietView(@PathVariable(name = "id") Integer id, Model model) {
        Map<Integer, Integer> DSSP = new HashMap<>();
        List<TaiNghe> listTN = new ArrayList<>();
        User user = userService.getById(id);
        model.addAttribute("US", user);
        List<HoaDon> listHD = hoaDonService.getAllByUser(user);
        for (HoaDon hd : listHD) {
            if (hd.getTinhTrang().equals("Chua thanh toan")) {
                List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getByHd(hd);
                for (HoaDonChiTiet hdct : listHDCT) {
                    listTN.add(hdct.getTaiNghe());
                    DSSP.put(hdct.getTaiNghe().getTaiNgheId(), hdct.getSoLuong());
                }
                BigDecimal tongTien = hoaDonService.tongTien(hd);
                model.addAttribute("lishHD", listHD);
                model.addAttribute("tongTien", tongTien);
                model.addAttribute("hoaDon", listTN);
                model.addAttribute("DSSP", DSSP);
            }
        }
        return "/Client/hoa-don";
    }

    @PostMapping("/addHD/{id}")
    public String addHD(@PathVariable(name = "id") Integer id, Model model) {
        Map<Integer, Integer> DSSP = new HashMap<>();
        List<TaiNghe> listTN = new ArrayList<>();
        User user = userService.getById(id);
        model.addAttribute("US", user);
        GioHang gh = gioHangService.getByUser(user);
        List<GioHangChiTiet> listGHCT = gioHangChiTietService.getByGh(gh);
        List<HoaDon> listHD = hoaDonService.getAllByUser(user);
        HoaDon hd = new HoaDon();
        hd.setUser(user);
        Date date = new Date();
        hd.setNgayTao(date);
        hd.setDiaChi(user.getDiaChi());
        hd.setTenNguoiNhan(user.getTen());
        hd.setTinhTrang("Chua thanh toan");
        hd = hoaDonService.addHD(hd);

        for (GioHangChiTiet ghct : listGHCT) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setTaiNghe(ghct.getTaiNghe());
            hoaDonChiTiet.setHd(hd);
            hoaDonChiTiet.setDonGia(ghct.getDonGia());
            hoaDonChiTiet.setSoLuong(ghct.getSoLuong());

            hoaDonChiTietService.saveHDCT(hoaDonChiTiet);
        }

        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getByHd(hd);
        for (HoaDonChiTiet hdct : listHDCT) {
            listTN.add(hdct.getTaiNghe());
            DSSP.put(hdct.getTaiNghe().getTaiNgheId(), hdct.getSoLuong());
        }


        BigDecimal tongTien = gioHangService.tongTien(gh);
        model.addAttribute("lishHD", listHD);
        model.addAttribute("tongTien", tongTien);
        model.addAttribute("hoaDon", listTN);
        model.addAttribute("DSSP", DSSP);

        return "redirect:/hoa-don/{id}";
    }

    @PostMapping("/update-hoa-don/{id}")
    public String updateHD(Model model, @PathVariable(name = "id") Integer id, @Validated HoaDon hd,
                           @Validated TaiNghe taiNghe,
                           BindingResult result) {
        User user = userService.getById(id);
        model.addAttribute("US", user);
        List<HoaDon> listhd = hoaDonService.getAllByUser(user);
        for (HoaDon hd1 : listhd) {
            if (result.hasErrors()) {
                hd.setId(hd1.getId());
                return "redirect:/hoa-don/{id}";
            }
            //update hoa don
            hd.setTinhTrang("Da Thanh toan");
            hd.setUser(user);
            hd.setId(hd1.getId());
            hd.setNgayTao(hd1.getNgayTao());
            hd.setTenNguoiNhan(hd1.getTenNguoiNhan());
            hd.setDiaChi(hd1.getDiaChi());
            hoaDonService.updateHd(hd);
        }
        //xoa gh
        GioHang gh = gioHangService.getByUser(user);
        List<GioHangChiTiet> listGHCT = gioHangChiTietService.getByGh(gh);
        for (GioHangChiTiet ghct : listGHCT) {
            gioHangChiTietService.deleteGHCT(ghct);
        }
//        xoa so luong sp
        List<HoaDonChiTiet> listHDCT = hoaDonChiTietService.getByHd(hd);
        for (HoaDonChiTiet hdct : listHDCT) {
            TaiNghe tn = hdct.getTaiNghe();
            if (result.hasErrors()) {
                taiNghe.setTaiNgheId(tn.getTaiNgheId());
                return "redirect:/hoa-don/{id}";
            }
            taiNghe.setSoLuongTon(tn.getSoLuongTon() - hdct.getSoLuong());
            taiNghe.setTaiNgheId(tn.getTaiNgheId());
            taiNghe.setDonGia(tn.getDonGia());
            taiNghe.setCongKetNoi(tn.getCongKetNoi());
            taiNghe.setTenTaiNghe(tn.getTenTaiNghe());
            taiNghe.setTanSo(tn.getTanSo());
            taiNghe.setKichThuocMangLoa(tn.getKichThuocMangLoa());
            taiNgheService.updateTaiNghe(taiNghe);
        }
        return "redirect:/client/{id}";
    }
}
