package com.white.assignmentjava5.service.impl;

import com.white.assignmentjava5.entity.SanPham;
import com.white.assignmentjava5.repository.ISanPhamRepository;
import com.white.assignmentjava5.service.ISanPhamService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SanPhamService implements ISanPhamService {
    ISanPhamRepository sanPhamRepository;
    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getByMa(String ma) {
        return sanPhamRepository.findByMaSanPham(ma).orElse(null);
    }

    @Override
    public SanPham insert(SanPham sanPham) {
        sanPham.setMaSanPham("SP" + System.currentTimeMillis());
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public SanPham update(SanPham sanPham,String ma) {
        SanPham sanPham1 = sanPhamRepository.findByMaSanPham(ma).orElse(null);
        assert sanPham1 != null;
        sanPham1.setTenSanPham(sanPham.getTenSanPham());
        sanPham1.setTrangThai(sanPham.isTrangThai());
        return sanPhamRepository.save(sanPham1);
    }

    @Override
    public void delete(String ma) {
        sanPhamRepository.deleteByMaSanPham(ma);
    }
}
