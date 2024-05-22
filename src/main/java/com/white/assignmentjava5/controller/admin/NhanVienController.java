package com.white.assignmentjava5.controller.admin;

import com.white.assignmentjava5.entity.NhanVien;
import com.white.assignmentjava5.service.INhanVienService;
import com.white.assignmentjava5.util.ValidateUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/admin/nhan-vien")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class NhanVienController {

    final INhanVienService nhanVienService;

    @GetMapping("/danh-sach-nhan-vien")
    public String danhSachNhanVien(Model model) {
        model.addAttribute("nhanViens", nhanVienService.getAll());
        return "page/admin/nhan-vien/nhan-vien-list.jsp";
    }
    @GetMapping("/them-nhan-vien")
    public String themNhanVien(Model model) {
        model.addAttribute("formLink", "/admin/nhan-vien/them-moi");
        return "page/admin/nhan-vien/nhan-vien-form.jsp";
    }
    @PostMapping("/them-moi")
    public String themMoi(@Valid NhanVien nhanVien, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("formLink", "/admin/nhan-vien/them-moi");
            model.addAttribute("nhanVien", nhanVien);
            return "page/admin/nhan-vien/nhan-vien-form.jsp";
        }
        NhanVien saveNhanVien = nhanVienService.insert(nhanVien);
        if (saveNhanVien == null) {
            model.addAttribute("errors", Map.of("tenDangNhap", "Tên đăng nhập đã tồn tại"));
            model.addAttribute("formLink", "/admin/nhan-vien/them-moi");
            model.addAttribute("nhanVien", nhanVien);
            return "page/admin/nhan-vien/nhan-vien-form.jsp";
        }

        return "redirect:/admin/nhan-vien/danh-sach-nhan-vien";
    }
    @GetMapping("/sua-nhan-vien/{maNhanVien}")
    public String suaNhanVien(Model model,@PathVariable("maNhanVien") String maNhanVien) {
        model.addAttribute("nhanVien", nhanVienService.getByMa(maNhanVien));
        model.addAttribute("formLink", "/admin/nhan-vien/cap-nhat/"+maNhanVien);
        return "page/admin/nhan-vien/nhan-vien-form.jsp";
    }
    @PostMapping("/cap-nhat/{maNhanVien}")
    public String capNhat(@Valid NhanVien nhanVien,
                          BindingResult bindingResult,
                          Model model,
                          @PathVariable("maNhanVien") String maNhanVien) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("formLink", "/admin/nhan-vien/cap-nhat/"+maNhanVien);
            model.addAttribute("nhanVien", nhanVien);
            return "page/admin/nhan-vien/nhan-vien-form.jsp";
        }
        nhanVienService.update(nhanVien, maNhanVien);
        return "redirect:/admin/nhan-vien/danh-sach-nhan-vien";
    }

}
