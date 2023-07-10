package com.example.asm.repository;

import com.example.asm.entities.GioHang;
import com.example.asm.entities.GioHangChiTiet;
import com.example.asm.entities.TaiNghe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IGioHangChiTietRepository extends JpaRepository<GioHangChiTiet, Integer> {
    GioHangChiTiet getByTaiNgheAndGh(TaiNghe taiNghe, GioHang gh);

    GioHangChiTiet findByGh(GioHang gh);

    List<GioHangChiTiet> getByGh(GioHang gh);

}
