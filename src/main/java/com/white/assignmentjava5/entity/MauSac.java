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
@Builder
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MauSac extends AbstractEntity{

    @Column(unique = true,nullable = false)
    String maMauSac;

    @Column(columnDefinition = "NVARCHAR(255)")
    @NotNull(message = "Tên màu sắc không được để trống")
    @Size(min = 1, max = 255, message = "Tên màu sắc phải từ 1 đến 255 ký tự")
    String tenMauSac;

    @JsonManagedReference
    @OneToMany(mappedBy = "mauSac")
    List<SanPhamChiTiet> sanPhamChiTiets;
}
