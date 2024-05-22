package com.white.assignmentjava5.repository;

import com.white.assignmentjava5.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMauSacRepository extends JpaRepository<MauSac, String> {
    Optional<MauSac> findByMaMauSac(String ma);
    void deleteByMaMauSac(String ma);
}
