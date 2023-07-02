package com.dinhson.sunshop.appAdmin.ordersManagement;

import com.dinhson.sunshop.appOrders.OrderService;
import com.dinhson.sunshop.appOrders.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class OrderManagementController {

    private final OrderService orderService;

    @GetMapping("admin/orders")
    public String adminHome(@RequestParam(required = false) String searchName,
                            @RequestParam(required = false) String statusChecked,
                            Model model){

        Status[] statuses = Status.values();
        List<OrderDTO> orders = orderService.searchOrderDTO(statusChecked, searchName);

        orders.forEach(m -> System.out.println("money: " + m.totalMoney()));

        model.addAttribute("orders", orders);
        model.addAttribute("statues", statuses);
        model.addAttribute("searchName", searchName);
        model.addAttribute("statusChecked", statusChecked);
        return "admin-orders-management";
    }
}
