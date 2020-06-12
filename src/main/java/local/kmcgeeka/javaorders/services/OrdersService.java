package local.kmcgeeka.javaorders.services;

import local.kmcgeeka.javaorders.models.Orders;

import java.util.List;

public interface OrdersService
{
    List <Orders> findAllOrders();
    Orders findByOrderNum(long id);
    Orders save(Orders order);
    void delete(long id);

}
