package com.white.assignmentjava5.controller.admin;

import com.white.assignmentjava5.entity.KichThuoc;
import com.white.assignmentjava5.service.IKichThuocService;
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

@Controller
@RequestMapping("/admin/kich-thuoc")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class KichThuocController {

    IKichThuocService kichThuocService;

    @GetMapping("/toan-bo-kich-thuoc")
    public String toanBoKichThuoc(Model model) {
        model.addAttribute("kichThuocs", kichThuocService.getAll());
        return "page/admin/kich-thuoc/kich-thuoc-list.jsp";
    }
    @GetMapping("/them-kich-thuoc")
    public String themKichThuocPage(Model model) {
        model.addAttribute("formLink", "/admin/kich-thuoc/them-moi");
        return "page/admin/kich-thuoc/kich-thuoc-form.jsp";
    }
    @PostMapping("/them-moi")
    public String themMoiKichThuoc(@Valid KichThuoc kichThuoc, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("kichThuoc", kichThuoc);
            return "page/admin/kich-thuoc/kich-thuoc-form.jsp";
        }
        kichThuocService.insert(kichThuoc);
        return "redirect:/admin/kich-thuoc/toan-bo-kich-thuoc";
    }
    @GetMapping("/sua-kich-thuoc/{maKichThuoc}")
    public String suaKichThuocPage(@PathVariable("maKichThuoc") String maKichThuoc, Model model) {
        model.addAttribute("kichThuoc", kichThuocService.getByMa(maKichThuoc));
        model.addAttribute("formLink", "/admin/kich-thuoc/cap-nhat/" + maKichThuoc);
        return "page/admin/kich-thuoc/kich-thuoc-form.jsp";
    }
    @PostMapping("/cap-nhat/{maKichThuoc}")
    public String capNhatKichThuoc(@PathVariable("maKichThuoc") String maKichThuoc, @Valid KichThuoc kichThuoc, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("kichThuoc", kichThuoc);
            return "page/admin/kich-thuoc/kich-thuoc-form.jsp";
        }
        kichThuocService.update(kichThuoc, maKichThuoc);
        return "redirect:/admin/kich-thuoc/toan-bo-kich-thuoc";
    }
}
