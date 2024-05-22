package com.white.assignmentjava5.service.impl;

import com.white.assignmentjava5.dto.CartDto;
import com.white.assignmentjava5.entity.HoaDon;
import com.white.assignmentjava5.entity.HoaDonChiTiet;
import com.white.assignmentjava5.entity.SanPhamChiTiet;
import com.white.assignmentjava5.enums.PhuongThucThanhToan;
import com.white.assignmentjava5.repository.IHoaDonChiTietRepository;
import com.white.assignmentjava5.repository.IHoaDonRepository;
import com.white.assignmentjava5.repository.IKhachHangRepository;
import com.white.assignmentjava5.repository.INhanVienRepository;
import com.white.assignmentjava5.repository.ISanPhamChiTietRepository;
import com.white.assignmentjava5.service.IHoaDonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class HoaDonService implements IHoaDonService {

    IHoaDonChiTietRepository hoaDonChiTietRepository;
    IHoaDonRepository hoaDonRepository;
    ISanPhamChiTietRepository sanPhamChiTietRepository;
    INhanVienRepository nhanVienRepository;
    IKhachHangRepository khachHangRepository;

    @Override
    @Transactional
    public void thanhToanHoaDon(List<CartDto.CartDtoRequest> cart, String username
            , boolean trangThai
            , String maKhacHang
            , PhuongThucThanhToan phuongThucThanhToan) {
        HoaDon hoaDon = createHoaDon(username, trangThai);
        hoaDon.setPhuongThucThanhToan(phuongThucThanhToan);
        hoaDon.setKhachHang(khachHangRepository.findByMaKhachHang(maKhacHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy khách hàng")));
        Map<String, CartDto.CartDtoRequest> cartMap = cart
                .stream()
                .collect(Collectors.toMap(CartDto.CartDtoRequest::getMaSanPhamChiTiet, Function.identity()));
        List<SanPhamChiTiet> sanPhamChiTiets = sanPhamChiTietRepository.findAllByMaSanPhamChiTietIn(new ArrayList<>(cartMap.keySet()));
        List<HoaDonChiTiet> hoaDonChiTiets = createHoaDonChiTiets(hoaDon, sanPhamChiTiets, cartMap);
        updateSanPhamChiTietSoLuong(sanPhamChiTiets, cartMap);
        int tongTien = hoaDonChiTiets.stream().mapToInt(hoaDonChiTiet -> hoaDonChiTiet.getSoLuong() * hoaDonChiTiet.getDonGia()).sum();
        hoaDon.setTongTien(tongTien);
        hoaDonRepository.save(hoaDon);
        hoaDonChiTietRepository.saveAll(hoaDonChiTiets);
        sanPhamChiTietRepository.saveAll(sanPhamChiTiets);
    }

    @Override
    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAllOrderByNgayMuaHang();
    }

    private HoaDon createHoaDon(String username, boolean trangThai) {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNhanVien(nhanVienRepository.findByTenDangNhap(username).orElseThrow(() ->
                new RuntimeException("Không tìm thấy nhân viên")));
        hoaDon.setTrangThai(trangThai);
        return hoaDon;
    }

    private List<HoaDonChiTiet> createHoaDonChiTiets(HoaDon hoaDon, List<SanPhamChiTiet> sanPhamChiTiets, Map<String, CartDto.CartDtoRequest> cartMap) {
        return sanPhamChiTiets.stream().map(sanPhamChiTiet -> {
            CartDto.CartDtoRequest cartDtoRequest = cartMap.get(sanPhamChiTiet.getMaSanPhamChiTiet());
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setTrangThai(true);
            hoaDonChiTiet.setSanPhamChiTiet(sanPhamChiTiet);
            hoaDonChiTiet.setSoLuong(cartDtoRequest.getSoLuong());
            hoaDonChiTiet.setDonGia(sanPhamChiTiet.getDonGia());
            return hoaDonChiTiet;
        }).collect(Collectors.toList());
    }

    private void updateSanPhamChiTietSoLuong(List<SanPhamChiTiet> sanPhamChiTiets, Map<String, CartDto.CartDtoRequest> cartMap) {
        sanPhamChiTiets.forEach(sanPhamChiTiet -> {
            CartDto.CartDtoRequest cartDtoRequest = cartMap.get(sanPhamChiTiet.getMaSanPhamChiTiet());
            sanPhamChiTiet.setSoLuong(sanPhamChiTiet.getSoLuong() - cartDtoRequest.getSoLuong());
            if (sanPhamChiTiet.getSoLuong() == 0) {
                sanPhamChiTiet.setTrangThai(false);
            }
        });
    }
}
