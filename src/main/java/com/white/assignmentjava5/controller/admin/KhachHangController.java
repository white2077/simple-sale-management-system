package com.white.assignmentjava5.controller.admin;

import com.white.assignmentjava5.entity.KhachHang;
import com.white.assignmentjava5.service.IKhachHangService;
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
@RequestMapping("/admin/khach-hang")
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class KhachHangController {

    final IKhachHangService khachHangService;

    @GetMapping("/danh-sach-khach-hang")
    public String danhSachKhachHang(Model model) {
        model.addAttribute("khachHangs", khachHangService.getAll());
        return "page/admin/khach-hang/khach-hang-list.jsp";
    }
    @GetMapping("/them-khach-hang")
    public String themKhachHang(Model model) {
        model.addAttribute("formLink", "/admin/khach-hang/them-moi");
        return "page/admin/khach-hang/khach-hang-form.jsp";
    }
    @PostMapping("/them-moi")
    public String themMoiKhachHang(@Valid KhachHang khachHang, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("khachHang", khachHang);
            return "page/admin/khach-hang/khach-hang-form.jsp";
        }
        KhachHang khachHang1 = khachHangService.insert(khachHang);
        if (khachHang1 == null) {
            model.addAttribute("errors", Map.of("soDienThoai", "Số điện thoại đã tồn tại"));
            model.addAttribute("khachHang", khachHang);
            return "page/admin/khach-hang/khach-hang-form.jsp";
        }
        return "redirect:/admin/khach-hang/danh-sach-khach-hang";
    }
    @GetMapping("/sua-khach-hang/{maKhachHang}")
    public String suaKhachHang(@PathVariable("maKhachHang") String maKhachHang, Model model) {
        model.addAttribute("formLink", "/admin/khach-hang/cap-nhat/"+maKhachHang);
        model.addAttribute("khachHang", khachHangService.getByMa(maKhachHang));
        return "page/admin/khach-hang/khach-hang-form.jsp";
    }
    @PostMapping("/cap-nhat/{maKhachHang}")
    public String capNhatKhachHang(@Valid KhachHang khachHang,
                                   BindingResult bindingResult,
                                   @PathVariable("maKhachHang") String maKhachHang, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("khachHang", khachHang);
            return "page/admin/khach-hang/khach-hang-form.jsp";
        }
        KhachHang khachHang1 = khachHangService.update(khachHang,maKhachHang);
        if (khachHang1 == null) {
            model.addAttribute("errors", Map.of("soDienThoai", "Số điện thoại đã tồn tại"));
            model.addAttribute("khachHang", khachHang);
            return "page/admin/khach-hang/khach-hang-form.jsp";
        }
        return "redirect:/admin/khach-hang/danh-sach-khach-hang";
    }
}
