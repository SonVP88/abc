package com.example.asm.service;

import com.example.asm.entities.GioHang;
import com.example.asm.entities.HoaDon;
import com.example.asm.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface IHoaDonService {
    HoaDon addHD(HoaDon hd);

    HoaDon getByUser(User u);

    BigDecimal tongTien(HoaDon hd);

    HoaDon updateHd(HoaDon hd);

    List<HoaDon> getAllByUser(User u);
}
