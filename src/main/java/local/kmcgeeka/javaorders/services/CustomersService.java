package local.kmcgeeka.javaorders.services;

import local.kmcgeeka.javaorders.models.Customers;
import local.kmcgeeka.javaorders.models.Orders;

import java.util.List;



public interface CustomersService
{
    void delete(long custcode);
    Customers findByCustomerCode(long id);
    List<Customers> findByNameLike(String thename);
    List <Customers> findAllCustomers();
    Customers save(Customers customers);

}
