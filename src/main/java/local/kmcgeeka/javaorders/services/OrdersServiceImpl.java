package local.kmcgeeka.javaorders.services;

import local.kmcgeeka.javaorders.models.Agents;
import local.kmcgeeka.javaorders.models.Customers;
import local.kmcgeeka.javaorders.models.Orders;

import local.kmcgeeka.javaorders.models.Payments;
import local.kmcgeeka.javaorders.repositories.CustomersRepository;
import local.kmcgeeka.javaorders.repositories.OrdersRepository;
import local.kmcgeeka.javaorders.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "ordersService")
public class OrdersServiceImpl implements OrdersService
{

    @Autowired
    private OrdersRepository ordersrepos;

    @Autowired
    private PaymentsRepository payrepos;

    @Autowired
    private CustomersService customersService;

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

    //DELETE
    @Transactional //do everything inside of this method ... if anything fails the entire thing fails
    //add transactional to the entire service and each individual methods ... how SPRING calls methods
    @Override
    public void delete(long id)
    {
        //removes info from database
        //VERIFY DATA FIRST
        //check to make sure it's a primary key of restaurant
        if (ordersrepos.findById(id)
            .isPresent())
        {
            //delete associated menu items and associated payments
            ordersrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException("Order " + id + " Not Found");  //exceptions -> when you throw an exception all processing STOPS (core Java thing)
        }//everything STOPS...SPRING reports to the client and then it waits for response
        //exception is something that happens outside the normal flow
    }//cascade all does this

    @Transactional @Override public Orders save(Orders order)
    {
        Orders newOrder = new Orders();
        //put does a complete replace of the information
        if (order.getOrdnum() != 0)
        {
            //put takes place
            //verify that id exists
            ordersrepos.findById(order.getOrdnum())
                .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));
            //getOrdernum is a getter in Orders
            newOrder.setOrdnum(order.getOrdnum());
        }
        newOrder.setOrdamount(order.getOrdamount());  //run it through this
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        //===========================================
        //Many to One -- many orders to one customers
        //===========================================
        //POST
        Customers currentCust = customersService.findByCustomerCode(order.getCustomer().getCustcode());
        newOrder.setCustomer(currentCust);
        //PUT
        // ==================
        // MANY TO MANY
        // ==================
        newOrder.getPayments()
            .clear();
        for (Payments p : order.getPayments())
        {
            Payments newPay = payrepos.findById(p.getPaymentid())
                .orElseThrow(() -> new EntityNotFoundException("Payments " + p.getPaymentid() + " Not Found"));
            newOrder.addPayment(newPay); //had to create addPayment inside Orders Model
        }

        return ordersrepos.save(newOrder); //when this is returned data in database is changed
    }


}
