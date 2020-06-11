package local.kmcgeeka.javaorders.services;

import local.kmcgeeka.javaorders.models.Orders;

import local.kmcgeeka.javaorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "ordersService")

public class OrdersServiceImpl implements OrdersService
{

    @Autowired
    private OrdersRepository ordersrepos;

    @Override
    public List<Orders> findAllOrders()
    {
        List<Orders> rtnList = new ArrayList<>();
        ordersrepos.findAll()
            .iterator()
            .forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Orders findByOrderNum(long id)
    {
        return ordersrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order " + id + "Not Found"));
    }
}
