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
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KichThuoc extends AbstractEntity {

    @Column(unique = true,nullable = false)
    String maKichThuoc;

    @NotNull(message = "Tên kích thước không được để trống")
    @Size(min = 1, max = 255, message = "Tên kích thước phải từ 1 đến 255 ký tự")
    @Column(columnDefinition = "NVARCHAR(255)")
    String tenKichThuoc;

    @JsonManagedReference
    @OneToMany(mappedBy = "kichThuoc")
    List<SanPhamChiTiet> sanPhamChiTiets;
}
