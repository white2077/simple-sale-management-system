package com.white.assignmentjava5.controller.seller;

import com.white.assignmentjava5.dto.CartDto;
import com.white.assignmentjava5.entity.NhanVien;
import com.white.assignmentjava5.enums.PhuongThucThanhToan;
import com.white.assignmentjava5.service.IHoaDonService;
import com.white.assignmentjava5.service.IKhachHangService;
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

    IHoaDonService hoaDonService;
    VNPayService vnPayService;
    IKhachHangService khachHangService;


    @PostMapping("/checkout")
    public String checkout(HttpSession session, @RequestParam("khachHang") String maKhachHang,
                           @RequestParam("phuongThucThanhToan") boolean phuongThucThanhToan,
                           HttpServletRequest request,Model model) {
        try {
            String username = ((NhanVien) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            List<CartDto.CartDtoRequest> cart = (List<CartDto.CartDtoRequest>) session.getAttribute("cart");

            if (phuongThucThanhToan) {
                hoaDonService.thanhToanHoaDon(cart, username, true, maKhachHang, PhuongThucThanhToan.TIEN_MAT);
            } else {
                session.setAttribute("maKhachHang", maKhachHang);
                long amount = cart.stream().mapToLong(CartDto.CartDtoRequest::getDonGia).sum();
                String paymentUrl = vnPayService.createVnPayPayment(request, amount);
                session.setAttribute("paymentUrl", paymentUrl);
                return "redirect:" + paymentUrl;
            }

            session.removeAttribute("cart");
            return "redirect:/seller/ban-hang/trang-ban-hang";
        }catch (Exception e){
            model.addAttribute("error", "Thanh toán thất bại");
            model.addAttribute("khachHangs", khachHangService.getAll());
            return "page/web/ban-hang.jsp";
        }
    }

    @GetMapping("/return")
    public String returnVnPay(@RequestParam ("vnp_ResponseCode") String responseCode, HttpSession session, Model model) {
        if (responseCode.equals("00")) {
            String username = ((NhanVien) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            List<CartDto.CartDtoRequest> cart = (List<CartDto.CartDtoRequest>) session.getAttribute("cart");
            String maKhachHang = (String) session.getAttribute("maKhachHang");
            hoaDonService.thanhToanHoaDon(cart, username, true, maKhachHang, PhuongThucThanhToan.CHUYEN_KHOAN_VNPAY);
            session.removeAttribute("cart");
            session.removeAttribute("maKhachHang");
            session.removeAttribute("paymentUrl");
            return "redirect:/seller/ban-hang/trang-ban-hang";
        } else {

            model.addAttribute("error", "Thanh toán thất bại");
            return "page/web/ban-hang.jsp";
        }
    }
}
