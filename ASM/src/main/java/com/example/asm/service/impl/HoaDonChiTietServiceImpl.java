package com.example.asm.service.impl;

import com.example.asm.entities.HoaDon;
import com.example.asm.entities.HoaDonChiTiet;
import com.example.asm.entities.TaiNghe;
import com.example.asm.repository.IHoaDonChiTietRepisitory;
import com.example.asm.service.IHoaDonChiTietService;
import com.example.asm.viewmodels.ThongKeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HoaDonChiTietServiceImpl implements IHoaDonChiTietService {

    @Autowired
    IHoaDonChiTietRepisitory repisitory;

    @Override
    public HoaDonChiTiet getByTaiNgheAndHd(TaiNghe tn, HoaDon hd) {
        return repisitory.getByTaiNgheAndAndHd(tn,hd);
    }

    @Override
    public List<HoaDonChiTiet> getByHd(HoaDon hd) {
        return repisitory.getByHd(hd);
    }

    @Override
    public HoaDonChiTiet saveHDCT(HoaDonChiTiet hdct) {
        return repisitory.save(hdct);
    }

    @Override
    public void deleteHDCT(HoaDonChiTiet hdct) {
        repisitory.delete(hdct);
    }

    @Override
    public List<ThongKeDTO> getTopTenBestSellingProducts() {
        List<Object[]> results = repisitory.findTop10TenBestSellingProducts();
        List<ThongKeDTO> thongKeList = new ArrayList<>();

        for (Object[] result : results) {
            String sanPham = (String) result[0];
            Long soLuongBan = (Long) result[1];
            ThongKeDTO thongKe = new ThongKeDTO(sanPham, soLuongBan);
            thongKeList.add(thongKe);
        }
        return thongKeList;
    }

}
