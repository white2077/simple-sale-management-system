package com.white.assignmentjava5.repository;

import com.white.assignmentjava5.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHoaDonRepository extends JpaRepository<HoaDon, String>{
    @Query("SELECT hd FROM HoaDon hd ORDER BY hd.ngayMuaHang DESC")
    List<HoaDon> findAllOrderByNgayMuaHang();
    @Query("SELECT SUM(hd.tongTien) FROM HoaDon hd WHERE MONTH(hd.ngayMuaHang) =:month")
    int revenueByMonth(@Param("month") int month);
}
