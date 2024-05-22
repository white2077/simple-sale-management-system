package com.white.assignmentjava5.service.impl;

import com.white.assignmentjava5.entity.KichThuoc;
import com.white.assignmentjava5.repository.IKichThuocRepository;
import com.white.assignmentjava5.service.IKichThuocService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class KichThuocService implements IKichThuocService {

    IKichThuocRepository kichThuocRepository;

    @Override
    public List<KichThuoc> getAll() {
        return kichThuocRepository.findAll();
    }

    @Override
    public KichThuoc getByMa(String ma) {
        return kichThuocRepository.findByMaKichThuoc(ma).orElse(null);
    }

    @Override
    public KichThuoc insert(KichThuoc kichThuoc) {
        kichThuoc.setMaKichThuoc("KT" + System.currentTimeMillis());
        return kichThuocRepository.save(kichThuoc);
    }

    @Override
    public KichThuoc update(KichThuoc kichThuoc, String ma) {
        KichThuoc kichThuocEntity = kichThuocRepository.findByMaKichThuoc(ma).orElse(null);
        if (kichThuocEntity == null) {
            return null;
        }
        kichThuocEntity.setTenKichThuoc(kichThuoc.getTenKichThuoc());
        kichThuocEntity.setTrangThai(kichThuoc.isTrangThai());
        return kichThuocRepository.save(kichThuocEntity);
    }

    @Override
    public void delete(String ma) {
        kichThuocRepository.deleteByMaKichThuoc(ma);
    }
}
