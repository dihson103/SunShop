package com.dinhson.sunshop.appOrders;

import com.dinhson.sunshop.appAdmin.ordersManagement.OrderDTO;
import com.dinhson.sunshop.appAdmin.ordersManagement.OrderDTOMapper;
import com.dinhson.sunshop.appAdmin.ordersManagement.OrderResponse;
import com.dinhson.sunshop.appAdmin.ordersManagement.OrderResponseMapper;
import com.dinhson.sunshop.appCart.CartItem;
import com.dinhson.sunshop.appCart.CartService;
import com.dinhson.sunshop.appOrders.orderdetails.OrderDetailService;
import com.dinhson.sunshop.appProduct.productDetails.ProductDetailService;
import com.dinhson.sunshop.appUser.UserService;
import com.dinhson.sunshop.appUser.shipments.Shipment;
import com.dinhson.sunshop.appUser.shipments.ShipmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final OrderItemDTOMapper orderItemDTOMapper;
    private final ShipmentService shipmentService;
    private final OrderDetailService orderDetailService;
    private final ProductDetailService productDetailService;
    private final OrderDTOMapper orderDTOMapper;
    private final OrderResponseMapper orderResponseMapper;

    private List<Integer> getItemsSelected (String itemsString){
        return Arrays.stream(itemsString.split(","))
                .map(item -> Integer.parseInt(item))
                .collect(Collectors.toList());
    }

    private boolean isCartItemOfUser(List<CartItem> cartItems, Integer userId){
        return !cartItems.stream()
                .anyMatch(cartItem -> cartItem.getUser().getId() != userId);
    }

    public List<OrderItemDTO> getOrderItems(String itemsString, Integer userId){
        List<Integer> itemIds = getItemsSelected(itemsString);
        List<CartItem> cartItems = cartService.getCartItemsById(itemIds);

        if(isCartItemOfUser(cartItems, userId)){
            return cartItems.stream()
                    .map(orderItemDTOMapper)
                    .collect(Collectors.toList());
        }

        throw new IllegalArgumentException("There are some products that are not in your cart!!");
    }

    public Double getSubTotal (List<OrderItemDTO> orderItemDTOS){
        return orderItemDTOS.stream()
                .mapToDouble(value -> value.totalPrice())
                .sum();
    }

    private Order createNewOrder (Shipment shipment, String note){
        LocalDate orderDate = LocalDate.now();
        Order order = Order.builder()
                .orderDate(orderDate)
                .status(Status.Pending)
                .note(note)
                .shipment(shipment)
                .build();
                //new Order(orderDate, Status.Pending, note, shipment);
        return orderRepository.save(order);
    }

    public void order(String checkedValues, Integer userId, Integer shipmentId, String newPhone, String newAddress, String note){
        if(shipmentId == 0 && newAddress == null && newPhone == null){
            throw new IllegalArgumentException("Please enter new shipment's information or choose an old shipment!!!");
        }

        List<Integer> itemIds = getItemsSelected(checkedValues);
        List<CartItem> cartItems = cartService.getCartItemsById(itemIds);

        Shipment shipment;
        //TODO find shipment
        if(shipmentId == 0){
            shipment = shipmentService.createNewShipment(newAddress, newPhone, userId);
        }else {
            shipment = shipmentService.findShipmentById(shipmentId);
        }

        //TODO add new order
        Order order = createNewOrder(shipment, note);

        //TODO create order details
        orderDetailService.creatOrderDetails(cartItems, order);

        //TODO tru so luong trong kho
        productDetailService.updateQuantityProducts(cartItems);

        //TODO delete items ordered in cart
        cartService.deleteCartItems(cartItems);
    }

    private List<Order> searchOrders(Status status, String searchName){
        if (status == null){
            if(searchName == null){
                return orderRepository.getAllOrders();
            }
            return orderRepository.searchOrdersBySearchName(searchName);
        }else {
            if(searchName == null){
                return orderRepository.searchOrdersByStatus(status);
            }
            return orderRepository.searchOrdersByAll(status, searchName);
        }
    }

    public List<OrderDTO> searchOrderDTO (String statusString, String searchName){
        Status status = null;
        for (Status s : Status.values()){
            if(s.name().equals(statusString)){
                status = s;
                break;
            }
        }

        return searchOrders(status, searchName).stream()
                .map(orderDTOMapper)
                .collect(Collectors.toList());
    }

    public OrderResponse getOrderResponseByOrderId(Integer orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Can not find order!!!"));
        return orderResponseMapper.apply(order);
    }



}
