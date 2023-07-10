package com.example.asm.service.impl;

import com.example.asm.entities.HoaDon;
import com.example.asm.entities.HoaDonChiTiet;
import com.example.asm.entities.User;
import com.example.asm.repository.IHoaDonRepository;
import com.example.asm.service.IHoaDonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HoaDonServiceImpl implements IHoaDonService {

    @Autowired
    IHoaDonRepository repository;

    @Override
    public HoaDon addHD(HoaDon hd) {
        return repository.save(hd);
    }

    @Override
    public HoaDon getByUser(User u) {
        return repository.getByUser(u);
    }

    @Override
    public BigDecimal tongTien(HoaDon hd) {
        double tongTien = 0;
        List<HoaDonChiTiet> listHDCT = hd.getListHDCT();
        for (HoaDonChiTiet hdct : listHDCT) {
            double thanhTien = hdct.getSoLuong() * Double.parseDouble(String.valueOf(hdct.getTaiNghe().getDonGia()));
            tongTien += thanhTien;
        }
        return BigDecimal.valueOf(tongTien);
    }

    @Override
    public HoaDon updateHd(HoaDon hd) {
        return repository.save(hd);
    }

    @Override
    public List<HoaDon> getAllByUser(User u) {
        return repository.findAllByUser(u);
    }
}
