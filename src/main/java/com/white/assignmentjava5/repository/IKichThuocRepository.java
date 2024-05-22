package com.white.assignmentjava5.repository;

import com.white.assignmentjava5.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IKichThuocRepository extends JpaRepository<KichThuoc, String>{
    Optional<KichThuoc> findByMaKichThuoc(String maKichThuoc);
    void deleteByMaKichThuoc(String maKichThuoc);
}
