package com.example.asm.repository;

import com.example.asm.entities.HoaDon;
import com.example.asm.entities.HoaDonChiTiet;
import com.example.asm.entities.TaiNghe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IHoaDonChiTietRepisitory extends JpaRepository<HoaDonChiTiet, Integer> {

    HoaDonChiTiet getByTaiNgheAndAndHd(TaiNghe tn, HoaDon hd);

    List<HoaDonChiTiet> getByHd(HoaDon hd);

    @Query("SELECT hdct.taiNghe.tenTaiNghe, SUM(hdct.soLuong) AS totalQuantity " +
            "FROM HoaDonChiTiet hdct " +
            "GROUP BY hdct.taiNghe.tenTaiNghe " +
            "ORDER BY totalQuantity DESC LIMIT 10")
    List<Object[]> findTop10TenBestSellingProducts();
}
