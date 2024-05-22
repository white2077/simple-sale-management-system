package com.white.assignmentjava5.service.impl;

import com.white.assignmentjava5.entity.NhanVien;
import com.white.assignmentjava5.repository.INhanVienRepository;
import com.white.assignmentjava5.security.role.Role;
import com.white.assignmentjava5.service.INhanVienService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class NhanVienService implements INhanVienService {

    final INhanVienRepository nhanVienRepository;

    @Override
    public NhanVien findByTenDangNhap(String tenDangNhap) {
        return nhanVienRepository.findByTenDangNhap(tenDangNhap).orElse(null);
    }

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.findAllByRoleLike(Role.SELLER);
    }

    @Override
    public NhanVien getByMa(String ma) {
        return nhanVienRepository.findByMaNhanVien(ma).orElse(null);
    }

    @Override
    public NhanVien insert(NhanVien nhanVien) {
       if (nhanVienRepository.existsByTenDangNhap(nhanVien.getTenDangNhap())) {
            return null;
       }
        nhanVien.setRole(Role.SELLER);
        nhanVien.setMatKhau(new BCryptPasswordEncoder().encode(nhanVien.getMatKhau()));
        return nhanVienRepository.save(nhanVien);
    }

    @Override
    public NhanVien update(NhanVien nhanVien, String ma) {
        NhanVien nhanVienEntity = nhanVienRepository.findByMaNhanVien(ma).orElse(null);
        if (nhanVienEntity != null) {
            nhanVienEntity.setTenNhanVien(nhanVien.getTenNhanVien());
            if (!nhanVien.getMatKhau().equals(nhanVienEntity.getMatKhau())) {
                nhanVienEntity.setMatKhau(new BCryptPasswordEncoder().encode(nhanVien.getMatKhau()));
            }
            else if (nhanVienEntity.getTenDangNhap().equals(nhanVien.getTenDangNhap())) {
                nhanVienEntity.setMaNhanVien(ma);
            }
            else if (!nhanVienRepository.existsByTenDangNhap(nhanVien.getTenDangNhap())) {
                nhanVienEntity.setMaNhanVien(ma);
            }
            else {
                return nhanVienRepository.save(nhanVienEntity);

            }
        }
        return null;
    }

    @Override
    public void delete(String ma) {
        nhanVienRepository.deleteById(ma);
    }
}
