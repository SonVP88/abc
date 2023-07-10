package com.example.asm.service;

import com.example.asm.entities.HoaDon;
import com.example.asm.entities.HoaDonChiTiet;
import com.example.asm.entities.TaiNghe;
import com.example.asm.viewmodels.ThongKeDTO;

import java.util.List;

public interface IHoaDonChiTietService {
    HoaDonChiTiet getByTaiNgheAndHd(TaiNghe tn, HoaDon hd);

    List<HoaDonChiTiet> getByHd(HoaDon hd);

    HoaDonChiTiet saveHDCT(HoaDonChiTiet hdct);

    void deleteHDCT(HoaDonChiTiet hdct);

    List<ThongKeDTO> getTopTenBestSellingProducts();

}
