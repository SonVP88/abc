package com.example.asm.service;

import com.example.asm.entities.GioHang;
import com.example.asm.entities.HoaDon;
import com.example.asm.entities.User;

import java.math.BigDecimal;
import java.util.List;

public interface IGioHangService {

    GioHang save(GioHang gh);

    GioHang getByUser(User user);

    BigDecimal tongTien(GioHang gh);

}
