package com.white.assignmentjava5.controller.seller;

import com.white.assignmentjava5.service.IChiTietSanPhamService;
import com.white.assignmentjava5.service.IKhachHangService;
import com.white.assignmentjava5.service.ISanPhamService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seller/ban-hang")
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class BanHangController {

    IChiTietSanPhamService chiTietSanPhamService;
    IKhachHangService khachHangService;

    @GetMapping("/trang-ban-hang")
    public String trangBanHang(Model model, HttpSession session) {
        String url = (String) session.getAttribute("paymentUrl");
        if (url != null) return "redirect:" + url;
        model.addAttribute("khachHangs", khachHangService.getAll());
        model.addAttribute("sanPhams", chiTietSanPhamService.getAll());
        return "page/web/ban-hang.jsp";
    }
}
