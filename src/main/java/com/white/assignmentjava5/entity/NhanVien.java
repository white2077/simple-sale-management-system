package com.white.assignmentjava5.entity;

import com.white.assignmentjava5.security.role.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanVien extends AbstractEntity implements UserDetails {

    @NotNull(message = "Tên nhân viên không được để trống")
    @Size(min = 1, max = 255, message = "Tên nhân viên phải từ 1 đến 255 ký tự")
    @Column(columnDefinition = "NVARCHAR(255)")
    String tenNhanVien;
    @Column(unique = true)
    String maNhanVien;
    @Column(unique = true)
    @NotNull(message = "Tên đăng nhập không được để trống")
    @Size(min = 1, max = 255, message = "Tên đăng nhập phải từ 1 đến 255 ký tự")
    String tenDangNhap;
    String matKhau;

    @OneToMany(mappedBy = "nhanVien")
    List<HoaDon> hoaDons;

    @Enumerated(EnumType.STRING)
    Role role;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return matKhau;
    }

    @Override
    public String getUsername() {
        return tenDangNhap;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isTrangThai();
    }

    @Override
    public boolean isAccountNonLocked() {
        return isTrangThai();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isTrangThai();
    }

    @Override
    public boolean isEnabled() {
        return isTrangThai();
    }
}