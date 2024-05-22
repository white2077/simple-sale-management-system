package com.white.assignmentjava5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class KhachHang extends AbstractEntity{
    @Column(unique = true,nullable = false)
    String maKhachHang;

    @NotNull(message = "Tên khách hàng không được để trống")
    @Column(columnDefinition = "NVARCHAR(255)")
    @NotNull(message = "Tên khách hàng không được để trống")
    String tenKhachHang;

    @Column(columnDefinition = "NVARCHAR(10)")
    @NotNull(message = "Số điện thoại không được để trống")
    @Size(min = 10, max = 10, message = "Số điện thoại phải có 10 số")
    @Pattern(regexp = "0[0-9]{9}", message = "Số điện thoại phải bắt đầu bằng số 0 và theo sau là 9 số")
    String soDienThoai;

    @OneToMany(mappedBy = "khachHang")
    List<HoaDon> hoaDons;
}
