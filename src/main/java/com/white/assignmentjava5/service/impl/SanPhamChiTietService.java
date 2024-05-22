package com.white.assignmentjava5.service.impl;

import com.white.assignmentjava5.entity.SanPhamChiTiet;
import com.white.assignmentjava5.repository.ISanPhamChiTietRepository;
import com.white.assignmentjava5.service.IChiTietSanPhamService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class SanPhamChiTietService implements IChiTietSanPhamService {
    ISanPhamChiTietRepository sanPhamChiTietRepository;

    @Override
    public boolean checkQuantity(String maSanPhamChiTiet, int soLuong) {
        SanPhamChiTiet sanPhamChiTiet = sanPhamChiTietRepository.findByMaSanPhamChiTiet(maSanPhamChiTiet).orElse(null);
        if (sanPhamChiTiet == null) {
            return false;
        }
        return sanPhamChiTiet.getSoLuong() >= soLuong;
    }

    @Override
    public List<SanPhamChiTiet> serachByTenSanPham(String tenSanPham) {
       return
               sanPhamChiTietRepository.
                       findAllBySanPhamTenSanPhamOrMaSanPhamLikeKeyword(tenSanPham, Pageable.ofSize(5),true).toList();
    }

    @Override
    public List<SanPhamChiTiet> getAll() {
        return sanPhamChiTietRepository.findAll();
    }

    @Override
    public List<SanPhamChiTiet> getAllByMaSanPham(String maSanPham) {
        return sanPhamChiTietRepository.findAllBySanPhamMaSanPham(maSanPham);
    }

    @Override
    public SanPhamChiTiet getbyMaSanPham(String maSanPham) {
        return sanPhamChiTietRepository.findByMaSanPhamChiTiet(maSanPham).orElse(null) ;
    }

    @Override
    public SanPhamChiTiet insert(SanPhamChiTiet sanPhamChiTiet) {
        sanPhamChiTiet.setMaSanPhamChiTiet(
                sanPhamChiTiet.getSanPham().getMaSanPham() +
                        sanPhamChiTiet.getKichThuoc().getMaKichThuoc() +
                        sanPhamChiTiet.getMauSac().getMaMauSac());
        return sanPhamChiTietRepository.save(sanPhamChiTiet);
    }

    @Override
    public SanPhamChiTiet update(SanPhamChiTiet sanPhamChiTiet, String maSanPham) {
        SanPhamChiTiet sanPhamChiTietEntity = sanPhamChiTietRepository.findByMaSanPhamChiTiet(maSanPham).orElse(null);
        if (sanPhamChiTietEntity == null) {
            return null;
        }
        sanPhamChiTietEntity.setSanPham(sanPhamChiTiet.getSanPham());
        sanPhamChiTietEntity.setKichThuoc(sanPhamChiTiet.getKichThuoc());
        sanPhamChiTietEntity.setMauSac(sanPhamChiTiet.getMauSac());
        sanPhamChiTietEntity.setSoLuong(sanPhamChiTiet.getSoLuong());
        sanPhamChiTietEntity.setDonGia(sanPhamChiTiet.getDonGia());
        return sanPhamChiTietRepository.save(sanPhamChiTietEntity);
    }

    @Override
    public void delete(String maSanPham) {
        sanPhamChiTietRepository.deleteBySanPhamMaSanPham(maSanPham);
    }
}
