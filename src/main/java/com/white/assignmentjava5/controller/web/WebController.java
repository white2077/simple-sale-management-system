package com.white.assignmentjava5.controller.web;

import com.white.assignmentjava5.service.IHoaDonService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class WebController {
    IHoaDonService hoaDonService;
    @RequestMapping("/trang-chu")
    public String home(Model model) {
        model.addAttribute("hoaDons", hoaDonService.getAllHoaDon());
        return "page/admin/dash-board/home.jsp";
    }
}
