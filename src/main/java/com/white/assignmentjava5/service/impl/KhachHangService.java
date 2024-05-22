package com.white.assignmentjava5.service.impl;

import com.white.assignmentjava5.entity.KhachHang;
import com.white.assignmentjava5.repository.IKhachHangRepository;
import com.white.assignmentjava5.service.IKhachHangService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class KhachHangService implements IKhachHangService {

    IKhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }


    @Override
    public KhachHang getByMa(String ma) {
        return khachHangRepository.findByMaKhachHang(ma).orElse(null);
    }


    @Override
    public KhachHang insert(KhachHang khachHang) {
       if (khachHangRepository.existsBySoDienThoai(khachHang.getSoDienThoai())) {
            return null;
        }
        khachHang.setMaKhachHang("KH" + System.currentTimeMillis());
        return khachHangRepository.save(khachHang);
    }

    @Override
    public KhachHang update(KhachHang khachHang, String ma) {
        KhachHang khachHangEntity = khachHangRepository.findByMaKhachHang(ma).orElse(null);
        if (khachHangEntity == null) {
            return null;
        }
        khachHangEntity.setTenKhachHang(khachHang.getTenKhachHang());
        if (!khachHangEntity.getSoDienThoai().equals(khachHang.getSoDienThoai())) {
            if (khachHangRepository.existsBySoDienThoai(khachHang.getSoDienThoai())) {
                return null;
            }
            khachHangEntity.setSoDienThoai(khachHang.getSoDienThoai());
        }
        khachHangEntity.setTrangThai(khachHang.isTrangThai());
        return khachHangRepository.save(khachHangEntity);
    }

    @Override
    public void delete(String ma) {
        khachHangRepository.deleteById(ma);
    }
}
