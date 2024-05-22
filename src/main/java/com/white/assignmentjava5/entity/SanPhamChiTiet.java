package com.white.assignmentjava5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamChiTiet extends AbstractEntity{

    @Column(unique = true,nullable = false)
    String maSanPhamChiTiet;
    @Min(value = 0, message = "Số lượng phải lớn hơn hoặc bằng 0")
    int soLuong;
    @Min(value = 1, message = "Đơn giá phải lớn hơn hoặc bằng 1")
    int donGia;
    @JsonBackReference
    @ManyToOne
    SanPham sanPham;

    @ManyToOne
    @JsonBackReference
    MauSac mauSac;

    @JsonBackReference
    @ManyToOne
    KichThuoc kichThuoc;
    @JsonIgnore
    @OneToMany(mappedBy = "sanPhamChiTiet")
    List<HoaDonChiTiet> hoaDonChiTiets;
}
