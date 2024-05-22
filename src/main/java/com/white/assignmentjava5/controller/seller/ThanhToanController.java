package com.white.assignmentjava5.controller.seller;

import com.white.assignmentjava5.dto.CartDto;
import com.white.assignmentjava5.entity.NhanVien;
import com.white.assignmentjava5.enums.PhuongThucThanhToan;
import com.white.assignmentjava5.service.IHoaDonService;
import com.white.assignmentjava5.service.INhanVienService;
import com.white.assignmentjava5.service.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/seller/thanh-toan")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class ThanhToanController {

    IHoaDonService HoaDonService;
    VNPayService vnPayService;

    @PostMapping("/checkout")
    public String checkout(Model model, HttpSession httpSession,
                           @RequestParam("khachHang") String maKhachHang,
                           @RequestParam("phuongThucThanhToan") boolean phuongThucThanhToan,
                           HttpServletRequest httpServletRequest) {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = ((NhanVien) principal).getUsername();
            List<CartDto.CartDtoRequest> cartDtoRequests =
                    (List<CartDto.CartDtoRequest>) httpSession.getAttribute("cart");
            if (phuongThucThanhToan){
                HoaDonService.thanhToanHoaDon(
                        cartDtoRequests,
                        username,
                        true,
                        maKhachHang, PhuongThucThanhToan.TIEN_MAT
                );

            }
            else {
                httpSession.setAttribute("maKhachHang", maKhachHang);
                long amount = cartDtoRequests.stream().mapToLong(CartDto.CartDtoRequest::getDonGia).sum();
                return "redirect:" + vnPayService.createVnPayPayment(httpServletRequest, amount);
            }
            httpSession.removeAttribute("cart");
            return "redirect:/seller/ban-hang/trang-ban-hang";
        } catch (Exception e) {
            model.addAttribute("error", "Thanh toán thất bại");
            return "page/web/ban-hang.jsp";
        }
    }

    @GetMapping("/return")
    public String returnVnPay(@RequestParam ("vnp_ResponseCode") String responseCode,
                              HttpSession httpSession,
                              Model model) {
        List<CartDto.CartDtoRequest> cartDtoRequests =
                (List<CartDto.CartDtoRequest>) httpSession.getAttribute("cart");
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((NhanVien) principal).getUsername();
        if (responseCode.equals("00")) {
            String maKhachHang = (String) httpSession.getAttribute("maKhachHang");
            HoaDonService.thanhToanHoaDon(
                    cartDtoRequests,
                    username,
                    true,
                    maKhachHang, PhuongThucThanhToan.CHUYEN_KHOAN_VNPAY);
            httpSession.removeAttribute("cart");
            httpSession.removeAttribute("maKhachHang");
            return "redirect:/seller/ban-hang/trang-ban-hang";
        }else {
            model.addAttribute("error", "Thanh toán thất bại");
            return "page/web/ban-hang.jsp";
        }
    }
}
