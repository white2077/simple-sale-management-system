package com.white.assignmentjava5.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonChiTiet extends AbstractEntity{

    @ManyToOne
    HoaDon hoaDon;
    @ManyToOne
    SanPhamChiTiet sanPhamChiTiet;
    @Min(value = 1, message = "Số lượng sản phẩm phải lớn hơn 0")
    int soLuong;
    @Min(value = 0, message = "Đơn giá sản phẩm phải lớn hơn 0")
    int donGia;
}
