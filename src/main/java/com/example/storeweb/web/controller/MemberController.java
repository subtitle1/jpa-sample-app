package com.example.storeweb.web.controller;

import com.example.storeweb.entity.Order;
import com.example.storeweb.service.OrderService;
import com.example.storeweb.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {

    private final OrderService orderService;

    @GetMapping("/order/list")
    public String orderList(Model model) {
        List<Order> orders = orderService.getOrders(SecurityUtils.getMemberId());
        model.addAttribute("orders", orders);

        return "member/order/list";
    }

    @GetMapping("/order/detail")
    public String orderDetail(@RequestParam("id") long orderId, Model model) {
        Order order = orderService.getOrderDetail(orderId);
        model.addAttribute("order", order);

        return "member/order/detail";
    }
}
