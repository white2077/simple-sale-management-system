package com.white.assignmentjava5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPham extends AbstractEntity{

    @Column(unique = true)
    String maSanPham;

    @NotNull(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 255, message = "Tên sản phẩm phải từ 5 đến 255 ký tự")
    @Column(unique = true,columnDefinition = "NVARCHAR(255)")
    String tenSanPham;

    @JsonManagedReference
    @OneToMany(mappedBy = "sanPham")
    List<SanPhamChiTiet> sanPhamChiTiets;
}
