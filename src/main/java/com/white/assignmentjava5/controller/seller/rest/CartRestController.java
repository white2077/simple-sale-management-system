package com.white.assignmentjava5.controller.seller.rest;

import com.white.assignmentjava5.dto.CartDto;
import com.white.assignmentjava5.service.IChiTietSanPhamService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import static com.white.assignmentjava5.dto.CartDto.*;

@RestController
@RequestMapping("/seller/api/seller/cart")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class CartRestController {

    IChiTietSanPhamService chiTietSanPhamService;

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody CartDtoRequest cartDto, HttpSession httpSession) {
        ArrayList<CartDtoRequest> cart = null;
        if (httpSession.getAttribute("cart") == null) {
            cart = new ArrayList<>();
            cart.add(cartDto);
            httpSession.setAttribute("cart",cart);
        } else {
            cart = (ArrayList<CartDtoRequest>) httpSession.getAttribute("cart");
            //error
            for (CartDtoRequest item : cart) {
                if (item.getMaSanPhamChiTiet().equals(cartDto.getMaSanPhamChiTiet())) {
                   int soLuong = item.getSoLuong() + cartDto.getSoLuong();
                   if (chiTietSanPhamService.checkQuantity(cartDto.getMaSanPhamChiTiet(), soLuong)) {
                       item.setSoLuong(soLuong);
                       item.setDonGia(item.getDonGia() + cartDto.getDonGia());
                       return ResponseEntity.ok(cart);
                   } else {
                       return ResponseEntity.badRequest().body(Map.of("error", "Số lượng sản phẩm không đủ "+ cartDto.getSanPham()));
                   }
                }
            }
                cart.add(cartDto);
            httpSession.setAttribute("cart", cart);
        }
        return ResponseEntity.ok(cart);
    }
    @GetMapping("/remove/{maSanPhamChiTiet}")
    public ResponseEntity<?> remove(@PathVariable("maSanPhamChiTiet") String maSanPhamChiTiet, HttpSession httpSession) {
    ArrayList<CartDtoRequest> cart = (ArrayList<CartDtoRequest>) httpSession.getAttribute("cart");
       for(int i = 0; i < cart.size(); i++) {
           if (Objects.equals(cart.get(i).getMaSanPhamChiTiet(), maSanPhamChiTiet)) {
               cart.remove(i);
               break;
           }
       }
        httpSession.setAttribute("cart", cart);
        return ResponseEntity.ok(cart);
    }
    @PostMapping("/decrease/{maSanPhamChiTiet}")
    public ResponseEntity <?> decrease(@PathVariable("maSanPhamChiTiet") String maSanPhamChiTiet, HttpSession httpSession){
        ArrayList<CartDtoRequest> cart = (ArrayList<CartDtoRequest>) httpSession.getAttribute("cart");
        for (CartDtoRequest item : cart) {
            if (Objects.equals(item.getMaSanPhamChiTiet(), maSanPhamChiTiet)) {
                if (item.getSoLuong() > 1) {
                    item.setSoLuong(item.getSoLuong() - 1);
                } else {
                    cart.remove(item);
                }
                break;
            }
        }
        httpSession.setAttribute("cart", cart);
        return ResponseEntity.ok(cart);

    }
    @GetMapping("/get-cart")
    public ResponseEntity<?> getCart(HttpSession httpSession) {
        ArrayList<CartDtoRequest> cart = (ArrayList<CartDtoRequest>) httpSession.getAttribute("cart");
        return ResponseEntity.ok(cart);
    }
}
