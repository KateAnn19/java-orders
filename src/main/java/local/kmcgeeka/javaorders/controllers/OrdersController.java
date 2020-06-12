package local.kmcgeeka.javaorders.controllers;


import local.kmcgeeka.javaorders.models.Orders;
import local.kmcgeeka.javaorders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    private OrdersService ordersService;

    //    http://localhost:2019/orders/order/7
    //    GET /orders/order/{id} - Returns the order and its customer with the given order number
    @GetMapping(value = "/order/{id}",
        produces = {"application/json"})
    public ResponseEntity<?> findOrderById(
        @PathVariable
            long id)
    {
        Orders o = ordersService.findByOrderNum(id);
        return new ResponseEntity<>(o,
            HttpStatus.OK);
    }

    //    POST /orders/order - adds a new order to an existing customer

    @PostMapping(value = "/order",
        consumes = {"application/json"})
    public ResponseEntity<?> addNewOrder(
        @Valid
        @RequestBody
            Orders newOrder)
    {
        newOrder.setOrdnum(0);
        newOrder = ordersService.save(newOrder);
        HttpHeaders responseHeaders = new HttpHeaders();
        //http://localhost:2019/orders/order/newid
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{orderid}")
            .buildAndExpand(newOrder.getOrdnum())
            .toUri();
        responseHeaders.setLocation(newOrderURI);
        //location header
        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }


    @PutMapping(value = "/order/{id}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateFullOrder(
        @Valid
        @RequestBody
            Orders newOrder,
        @PathVariable
            long id)
    {
        newOrder.setOrdnum(id);
        ordersService.save(newOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //
    //    PUT /orders/order/{ordernum} - completely replaces the given order record
    //
    //    DELETE /orders/order/{ordername} - deletes the given order
    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> deleteOrdersById(
        @PathVariable
            long id)
    {
        //no need to do produces JSON because nothing is returned
        ordersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
