package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appUser.UserDTO;
import com.dinhson.sunshop.appUser.UserService;
import com.dinhson.sunshop.appUser.shipments.Shipment;
import com.dinhson.sunshop.appUser.shipments.ShipmentService;
import com.dinhson.sunshop.securityConfig.MyUserDetail;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;
    private final ShipmentService shipmentService;
    private final UserService userService;

    @GetMapping
    public String checkOut(@AuthenticationPrincipal MyUserDetail user,
                           @RequestParam String checkedValues,
                           Model model){
        int userId = user.getId();

        List<OrderItemDTO> orderItemDTOS = orderService.getOrderItems(checkedValues,userId);
        Double subTotalPrice = orderService.getSubTotal(orderItemDTOS);
        List<Shipment> shipments = shipmentService.getShipmentsByUserId(userId);
        UserDTO userDTO = userService.getUserDTO(userId);

        model.addAttribute("user", userDTO);
        model.addAttribute("shipments", shipments);
        model.addAttribute("orderItemDTOS", orderItemDTOS);
        model.addAttribute("subTotal", subTotalPrice);
        model.addAttribute("checkedValues", checkedValues);
        return "checkout";
    }

    @PostMapping
    @Transactional(rollbackFor = {IllegalArgumentException.class})
    public String order(@AuthenticationPrincipal MyUserDetail user,
                        @RequestParam Integer shipment,
                        @RequestParam(required = false) String newPhone,
                        @RequestParam(required = false) String newAddress,
                        @RequestParam String checkedValues,
                        @RequestParam(required = false) String note,
                        RedirectAttributes redirectAttributes){

        orderService.order(checkedValues, user.getId(), shipment, newPhone, newAddress, note);
        redirectAttributes.addFlashAttribute("message", "Ordered successfully!!!");
        return "redirect:/shop";
    }

}
