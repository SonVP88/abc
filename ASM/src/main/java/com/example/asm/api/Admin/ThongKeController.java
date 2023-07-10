package com.example.asm.api.Admin;

import com.example.asm.service.IHoaDonChiTietService;
import com.example.asm.service.ITaiNgheService;
import com.example.asm.viewmodels.ThongKeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping
public class ThongKeController {
    @Autowired
    IHoaDonChiTietService hoaDonChiTietService;

    @Autowired
    ITaiNgheService taiNgheService;

    @GetMapping("/thongke")
    public ResponseEntity<List<ThongKeDTO>> getThongKe() {
        List<ThongKeDTO> thongKeList = hoaDonChiTietService.getTopTenBestSellingProducts();

        return ResponseEntity.ok(thongKeList);
    }
}
