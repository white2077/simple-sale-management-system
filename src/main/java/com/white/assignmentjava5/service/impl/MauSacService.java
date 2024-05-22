package com.white.assignmentjava5.service.impl;

import com.white.assignmentjava5.entity.MauSac;
import com.white.assignmentjava5.repository.IMauSacRepository;
import com.white.assignmentjava5.service.IMauSacService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MauSacService implements IMauSacService {

    IMauSacRepository mauSacRepository;
    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public MauSac getByMa(String ma) {
        return mauSacRepository.findByMaMauSac(ma).orElse(null);
    }

    @Override
    public MauSac insert(MauSac mauSac) {
        mauSac.setMaMauSac("MS" + System.currentTimeMillis());
        return mauSacRepository.save(mauSac);
    }

    @Override
    public MauSac update(MauSac mauSac, String ma) {
        MauSac mauSacEntity = mauSacRepository.findByMaMauSac(ma).orElse(null);
        if (mauSacEntity == null) {
            return null;
        }
        mauSacEntity.setTenMauSac(mauSac.getTenMauSac());
        mauSacEntity.setTrangThai(mauSac.isTrangThai());
        return mauSacRepository.save(mauSacEntity);
    }

    @Override
    public void delete(String ma) {
        mauSacRepository.deleteByMaMauSac(ma);
    }
}
