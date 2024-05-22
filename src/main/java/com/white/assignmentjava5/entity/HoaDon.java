package com.white.assignmentjava5.entity;

import com.white.assignmentjava5.enums.PhuongThucThanhToan;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDon extends AbstractEntity {
    Timestamp ngayMuaHang;

    @ManyToOne
    NhanVien nhanVien;
    @ManyToOne
    KhachHang khachHang;
    int tongTien;

    @Enumerated(EnumType.STRING)
    PhuongThucThanhToan phuongThucThanhToan;

    @OneToMany(mappedBy = "hoaDon")
    List<HoaDonChiTiet> hoaDonChiTiets;

    @PrePersist
    public void prePersist() {
        this.ngayMuaHang = new Timestamp(System.currentTimeMillis());
    }
}
