package com.white.assignmentjava5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

public abstract class CartDto {
    @FieldDefaults(level = lombok.AccessLevel.PRIVATE)
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class CartDtoRequest {
        String maSanPhamChiTiet;
        String sanPham;
        int soLuong;
        int donGia;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class SanPhamChiTietDTO {
        String maSanPhamChiTiet;
        String sanPham;
        int soLuong;
        int donGia;
    }
}
