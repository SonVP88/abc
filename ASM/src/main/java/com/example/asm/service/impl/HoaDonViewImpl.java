package com.example.asm.service.impl;

import com.example.asm.repository.IHoaDonViewRepository;
import com.example.asm.service.IHoaDonViewService;
import com.example.asm.viewmodels.HoaDonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoaDonViewImpl implements IHoaDonViewService {

    @Autowired
    IHoaDonViewRepository repository;

    @Override
    public HoaDonView save(HoaDonView hdv) {
        return repository.save(hdv);
    }
}
