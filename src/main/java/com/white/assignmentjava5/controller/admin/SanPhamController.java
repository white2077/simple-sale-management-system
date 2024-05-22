package com.white.assignmentjava5.controller.admin;

import com.white.assignmentjava5.entity.SanPham;
import com.white.assignmentjava5.service.impl.SanPhamService;
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
@RequestMapping("/admin/san-pham")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class SanPhamController {

    SanPhamService sanPhamService;


    @GetMapping("/toan-bo-san-pham")
    public String allSanPham(Model model){
        model.addAttribute("sanPhams", sanPhamService.getAll());
        return "page/admin/san-pham/san-pham-list.jsp";
    }
    @GetMapping("/them-san-pham")
    public String addSanPhamPage(Model model){
        model.addAttribute("formLink", "/admin/san-pham/them-moi");
        return "page/admin/san-pham/san-pham-form.jsp";
    }
    @PostMapping("/them-moi")
    public String addNewSanPham(@Valid SanPham sanPham
            , BindingResult bindingResult
            ,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("sanPham", sanPham);
            return "page/admin/san-pham/san-pham-form.jsp";
        }
        sanPhamService.insert(sanPham);
        return "redirect:/admin/san-pham/toan-bo-san-pham";
    }
    @GetMapping("/sua-san-pham/{maSanPham}")
    public String editSanPhamPage(@PathVariable("maSanPham") String maSanPham, Model model){
        model.addAttribute("sanPham", sanPhamService.getByMa(maSanPham));
        model.addAttribute("formLink", "/admin/san-pham/cap-nhat/"+maSanPham);
        return "page/admin/san-pham/san-pham-form.jsp";
    }
    @PostMapping("/cap-nhat/{maSanPham}")
    public String updateSanPham(@PathVariable("maSanPham") String maSanPham,
                                @Valid SanPham sanPham,
                                BindingResult bindingResult,
                                Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("sanPham", sanPham);
            return "page/admin/san-pham/san-pham-form.jsp";
        }
        sanPhamService.update(sanPham,maSanPham);
        return "redirect:/admin/san-pham/toan-bo-san-pham";
    }
}
