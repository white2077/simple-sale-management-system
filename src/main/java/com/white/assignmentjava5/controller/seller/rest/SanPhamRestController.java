package com.white.assignmentjava5.controller.seller.rest;

import com.white.assignmentjava5.dto.CartDto;
import com.white.assignmentjava5.entity.SanPhamChiTiet;
import com.white.assignmentjava5.service.IChiTietSanPhamService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@FieldDefaults(makeFinal = true,level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/seller/api/seller/san-pham")
public class SanPhamRestController {

    final IChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam String tenSanPham) {
        List<CartDto.SanPhamChiTietDTO> sanPhamChiTiets = chiTietSanPhamService.serachByTenSanPham(tenSanPham).stream().map(sanPhamChiTiet -> CartDto.SanPhamChiTietDTO.builder()
                .maSanPhamChiTiet(sanPhamChiTiet.getMaSanPhamChiTiet())
                .sanPham(sanPhamChiTiet.getSanPham().getTenSanPham()+ " - " + sanPhamChiTiet.getKichThuoc().getTenKichThuoc() + " - " + sanPhamChiTiet.getMauSac().getTenMauSac())
                .soLuong(sanPhamChiTiet.getSoLuong())
                .donGia(sanPhamChiTiet.getDonGia())
                .build()).toList();
        return ResponseEntity.ok(sanPhamChiTiets);
    }}
