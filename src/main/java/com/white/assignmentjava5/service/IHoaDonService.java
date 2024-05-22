package com.white.assignmentjava5.service;

import com.white.assignmentjava5.dto.CartDto;
import com.white.assignmentjava5.entity.HoaDon;
import com.white.assignmentjava5.enums.PhuongThucThanhToan;

import java.util.List;

public interface IHoaDonService {
    void thanhToanHoaDon(List<CartDto.CartDtoRequest> cart, String username, boolean trangThai, String maKhacHang,
                         PhuongThucThanhToan phuongThucThanhToan);
    List<HoaDon> getAllHoaDon();
}
