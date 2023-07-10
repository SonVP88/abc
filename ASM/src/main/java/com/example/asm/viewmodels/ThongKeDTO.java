package com.example.asm.viewmodels;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class ThongKeDTO {
    private String sanPham;
    private Long soLuongBan;

    public ThongKeDTO(String sanPham, Long soLuongBan) {
        this.sanPham = sanPham;
        this.soLuongBan = soLuongBan;
    }
}
