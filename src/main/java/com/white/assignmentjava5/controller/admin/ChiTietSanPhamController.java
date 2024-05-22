package com.white.assignmentjava5.controller.admin;

import com.white.assignmentjava5.entity.SanPham;
import com.white.assignmentjava5.entity.SanPhamChiTiet;
import com.white.assignmentjava5.service.IChiTietSanPhamService;
import com.white.assignmentjava5.service.IKichThuocService;
import com.white.assignmentjava5.service.IMauSacService;
import com.white.assignmentjava5.service.ISanPhamService;
import com.white.assignmentjava5.util.ValidateUtils;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/chi-tiet-san-pham")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ChiTietSanPhamController {
    final IChiTietSanPhamService chiTietSanPhamService;
    final IKichThuocService kichThuocService;
    final ISanPhamService sanPhamService;
    final IMauSacService mauSacService;
    SanPham sanPham;

    @GetMapping("/toan-bo-chi-tiet-san-pham/{maSanPham}")
    public String getChiTietSanPham(@PathVariable("maSanPham") String maSanPham, Model model) {
        this.sanPham = sanPhamService.getByMa(maSanPham);
        setModel(model, maSanPham);
        return "page/admin/san-pham-chi-tiet/chi-tiet-san-pham.jsp";
    }

    @PostMapping("/them-chi-tiet-san-pham")
    public String themChiTietSanPham(
            @RequestParam("maKichThuoc") String maKichThuoc,
            @RequestParam("maMauSac") String maMauSac,
            @Valid SanPhamChiTiet sanPhamChiTiet, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            setModel(model, sanPham.getMaSanPham());
            model.addAttribute("error", ValidateUtils.validate(bindingResult));
            return "page/admin/san-pham-chi-tiet/chi-tiet-san-pham.jsp";
        }
        sanPhamChiTiet.setSanPham(sanPham);
        sanPhamChiTiet.setKichThuoc(kichThuocService.getByMa(maKichThuoc));
        sanPhamChiTiet.setMauSac(mauSacService.getByMa(maMauSac));
        chiTietSanPhamService.insert(sanPhamChiTiet);
        return "redirect:/admin/chi-tiet-san-pham/toan-bo-chi-tiet-san-pham/" + sanPham.getMaSanPham();
    }
    @GetMapping("/cap-nhat-chi-tiet-san-pham/{maChiTietSanPham}")
    public String capNhatChiTietSanPham(@PathVariable("maChiTietSanPham") String maChiTietSanPham, Model model) {
        setModel(model, sanPham.getMaSanPham());
        model.addAttribute("chiTietSanPham", chiTietSanPhamService.getbyMaSanPham(maChiTietSanPham));
        return "page/admin/san-pham-chi-tiet/chi-tiet-san-pham-update-form.jsp";
    }
    @PostMapping("/cap-nhat/{maSanPhamChiTiet}")
    public String capNhatChiTietSanPham(
            @RequestParam("maKichThuoc") String maKichThuoc,
            @RequestParam("maMauSac") String maMauSac,
            @Valid SanPhamChiTiet sanPhamChiTiet, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            setModel(model, sanPham.getMaSanPham());
            model.addAttribute("error", ValidateUtils.validate(bindingResult));
            return "page/admin/san-pham-chi-tiet/chi-tiet-san-pham-update-form.jsp";
        }
        sanPhamChiTiet.setSanPham(sanPham);
        sanPhamChiTiet.setKichThuoc(kichThuocService.getByMa(maKichThuoc));
        sanPhamChiTiet.setMauSac(mauSacService.getByMa(maMauSac));
        chiTietSanPhamService.update(sanPhamChiTiet, sanPhamChiTiet.getMaSanPhamChiTiet());
        return "redirect:/admin/chi-tiet-san-pham/toan-bo-chi-tiet-san-pham/" + sanPham.getMaSanPham();
    }


    private void setModel(Model model,String maChiTietSanPham) {
        model.addAttribute("sanPham", sanPham);
        model.addAttribute("kichThuocs", kichThuocService.getAll());
        model.addAttribute("mauSacs", mauSacService.getAll());
        model.addAttribute("chiTietSanPhams", chiTietSanPhamService.getAllByMaSanPham(maChiTietSanPham));
    }
}
