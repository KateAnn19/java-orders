package local.kmcgeeka.javaorders.controllers;

import local.kmcgeeka.javaorders.models.Customers;
import local.kmcgeeka.javaorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")

public class CustomersController
{
    @Autowired
    private CustomersService customerService;

    //    http://localhost:2019/customers/orders
    //    GET /customers/orders - Returns all customers with their orders
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers(){
        List<Customers> myCustomers = customerService.findAllCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }
    //
    //    http://localhost:2019/customers/customer/7
    //    GET /customers/customer/{id} - Returns the customer and their orders with the given customer id
    @GetMapping(value = "/customers/{id}", produces = {"application/json"})
    public ResponseEntity<?> findRestaurantById(@PathVariable long id){
        Customers c = customerService.findByCustomerCode(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    //
    //    http://localhost:2019/customers/customer/77
    //    GET /customers/namelike/{likename} - Returns all customers and their orders with a customer name containing the given substring
    //
    //    http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/customers/namelike/{thename}")
    public ResponseEntity<?> listAllCstomersLikeName(@PathVariable String thename){
        List<Customers> myCustomers = customerService.findByNameLike(thename);
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

}
