package com.white.assignmentjava5.controller.admin;

import com.white.assignmentjava5.entity.MauSac;
import com.white.assignmentjava5.service.impl.MauSacService;
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

@Controller
@RequestMapping("/admin/mau-sac")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MauSacController {
    MauSacService mauSacService;
    @GetMapping("/toan-bo-mau-sac")
    public String allMauSac(Model model) {
        model.addAttribute("mauSacs", mauSacService.getAll());
        return "page/admin/mau-sac/mau-sac-list.jsp";
    }
    @GetMapping("/them-mau-sac")
    public String addMauSacPage(Model model) {
        model.addAttribute("formLink", "/admin/mau-sac/them-moi");
        return "page/admin/mau-sac/mau-sac-form.jsp";
    }
    @PostMapping("/them-moi")
    public String addNewMauSac(@Valid MauSac mauSac,BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("mauSac", mauSac);
            return "page/admin/mau-sac/mau-sac-form.jsp";
        }
        mauSacService.insert(mauSac);
        return "redirect:/admin/mau-sac/toan-bo-mau-sac";
    }
    @GetMapping("/sua-mau-sac/{maMauSac}")
    public String editMauSacPage(@PathVariable("maMauSac") String maMauSac, Model model) {
        model.addAttribute("mauSac", mauSacService.getByMa(maMauSac));
        model.addAttribute("formLink", "/admin/mau-sac/cap-nhat/" + maMauSac);
        return "page/admin/mau-sac/mau-sac-form.jsp";
    }
    @PostMapping("/cap-nhat/{maMauSac}")
    public String updateMauSac(@PathVariable("maMauSac") String maMauSac, @Valid MauSac mauSac, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", ValidateUtils.validate(bindingResult));
            model.addAttribute("mauSac", mauSac);
            return "page/admin/mau-sac/mau-sac-form.jsp";
        }
        mauSacService.update(mauSac,maMauSac);
        return "redirect:/admin/mau-sac/toan-bo-mau-sac";
    }
}
