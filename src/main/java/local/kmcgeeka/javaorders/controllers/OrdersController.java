package local.kmcgeeka.javaorders.controllers;


import local.kmcgeeka.javaorders.models.Orders;
import local.kmcgeeka.javaorders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    private OrdersService ordersService;
    //    http://localhost:2019/orders/order/7
    //    GET /orders/order/{id} - Returns the order and its customer with the given order number
    @GetMapping(value = "/order/{id}", produces = {"application/json"})
    public ResponseEntity<?> findOrderById(@PathVariable long id){
        Orders o = ordersService.findByOrderNum(id);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }
}
