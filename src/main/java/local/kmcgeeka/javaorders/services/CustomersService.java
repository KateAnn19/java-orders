package local.kmcgeeka.javaorders.services;

import local.kmcgeeka.javaorders.models.Customers;
import local.kmcgeeka.javaorders.models.Orders;

import java.util.List;



public interface CustomersService
{
    Customers findByCustomerCode(long id);
    List<Customers> findByNameLike(String thename);
    List <Customers> findAllCustomers();
}
