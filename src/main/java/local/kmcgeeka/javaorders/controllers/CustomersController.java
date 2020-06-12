package local.kmcgeeka.javaorders.controllers;

import local.kmcgeeka.javaorders.models.Customers;
import local.kmcgeeka.javaorders.models.Orders;
import local.kmcgeeka.javaorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    //    http://localhost:2019/customers/customers/7
    //    GET /customers/customer/{id} - Returns the customer and their orders with the given customer id
    @GetMapping(value = "/customers/{id}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerById(@PathVariable long id){
        Customers c = customerService.findByCustomerCode(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
    //
    //    http://localhost:2019/customers/customer/77
    //    GET /customers/namelike/{likename} - Returns all customers and their orders with a customer name containing the given substring
    //
    //    http://localhost:2019/customers/customers/namelike/mes
    @GetMapping(value = "/customers/namelike/{thename}")
    public ResponseEntity<?> listAllCustomersLikeName(@PathVariable String thename){
        List<Customers> myCustomers = customerService.findByNameLike(thename);
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

//POST /customers/customer - Adds a new customer including any new orders
    @PostMapping(value = "/customer",
        consumes = {"application/json"})
    public ResponseEntity<?> addNewOrder(@Valid @RequestBody Customers newCust)
    {
        newCust.setCustcode(0);
        newCust = customerService.save(newCust);
        HttpHeaders responseHeaders = new HttpHeaders();
        //http://localhost:2019/customers/customer/newid
        URI newCustURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{custid}")
            .buildAndExpand(newCust.getCustcode())
            .toUri();
        responseHeaders.setLocation(newCustURI);
        //location header
        return new ResponseEntity<>(null,
            responseHeaders,
            HttpStatus.CREATED);
    }

//PUT /customers/customer/{custcode} - completely replaces the customer record including associated orders with the provided data
    @PutMapping(value = "/customer/{id}",
        consumes = {"application/json"})
    public ResponseEntity<?> updateFullOrder(
        @Valid
        @RequestBody
            Customers newCust,
        @PathVariable
            long id)
    {
        newCust.setCustcode(id);
        customerService.save(newCust);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // PATCH /customers/customer/{custcode} - updates customers with the new data. Only the new data is to be sent from the frontend client.
    //update not replace
    @PatchMapping(value = "/customer/{custcode}", consumes = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateCustomer, @PathVariable long custcode)
    {
        customerService.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //DELETE /customers/customer/{custcode} - Deletes the given customer including any associated orders
    @DeleteMapping("/customers/{custcode}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable long custcode)
    {
    //no need to do produces JSON because nothing is returned
        customerService.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
