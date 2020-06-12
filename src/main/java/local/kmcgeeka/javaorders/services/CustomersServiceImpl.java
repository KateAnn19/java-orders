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
@Service(value = "customerService")
public class CustomersServiceImpl implements CustomersService
{
    @Autowired
    private CustomersRepository custrepos;

    @Autowired
    private PaymentsRepository payrepos;

    @Autowired
    private OrdersRepository ordersrepos;

    @Autowired
    private AgentsService agentsService;

    @Override
    public Customers findByCustomerCode(long id)
    {
        return custrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + "Not Found"));
    }

    @Override
    public List<Customers> findByNameLike(String thename)
    {
        List<Customers> list = custrepos.findByCustnameContainingIgnoringCase(thename);
        return list;
//        return custrepos.findByCustnameContainingIgnoringCase(thename);
    }

    @Override
    public List<Customers> findAllCustomers()
    {
        List<Customers> rtnList = new ArrayList<>();
        custrepos.findAll()
            .iterator()
            .forEachRemaining(rtnList::add);
        return rtnList;
    }


    @Transactional //do everything inside of this method ... if anything fails the entire thing fails
    //add transactional to the entire service and each individual methods ... how SPRING calls methods
    @Override
    public void delete(long custcode)
    {
        //removes info from database
        //VERIFY DATA FIRST
        //check to make sure it's a primary key of restaurant
        if (custrepos.findById(custcode)
            .isPresent())
        {
            //delete associated menu items and associated payments
            custrepos.deleteById(custcode);
        } else
        {
            throw new EntityNotFoundException("Customer " + custcode + " Not Found");  //exceptions -> when you throw an exception all processing STOPS (core Java thing)
        }//everything STOPS...SPRING reports to the client and then it waits for response
        //exception is something that happens outside the normal flow
    }//cascade all does this



    @Transactional @Override public Customers save(Customers customer)
    {
        Customers newCust = new Customers();
        //put does a complete replace of the information
        if (customer.getCustcode() != 0)
        {
            //put takes place
            //verify that id exists
            custrepos.findById(customer.getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));
            //getOrdernum is a getter in Orders
            newCust.setCustcode(customer.getCustcode());
        }
        newCust.setCustname(customer.getCustname());
        newCust.setCustcity(customer.getCustcity());
        newCust.setWorkingarea(customer.getWorkingarea());
        newCust.setCustcountry(customer.getCustcountry());
        newCust.setGrade(customer.getGrade());
        newCust.setOpeningamt(customer.getOpeningamt());
        newCust.setReceiveamt(customer.getReceiveamt());
        newCust.setOutstandingamt(customer.getOutstandingamt());
        newCust.setPhone(customer.getPhone());
        //===========================================
        //One to many -- one customer can have many orderes
        //===========================================
        for (Orders o : customer.getOrders())
        {
            Orders newOrder = new Orders(
                o.getOrdamount(),
                o.getAdvanceamount(), newCust,
                o.getOrderdescription());
            newCust.getOrders().add(newOrder);
        }
        //===========================================
        //Many to One -- many orders to one customers
        //===========================================
        //        Agents agent
        //POST
        Agents currentAgent = agentsService.findAgentByCode(customer.getAgent().getAgentcode());
        newCust.setAgent(currentAgent);
        return custrepos.save(newCust); //when this is returned data in database is changed
    }


//PATCH
@Transactional
@Override
    public Customers update(Customers customer, long id)
    {
        Customers currCust = findByCustomerCode(id);
        if(customer.getCustname() != null)
        {
            currCust.setCustname(customer.getCustname().toLowerCase());
        }
        if(customer.getCustcity() != null)
        {
            currCust.setCustcity(customer.getCustcity().toLowerCase());
        }
        if(customer.getWorkingarea() != null)
        {
            currCust.setWorkingarea(customer.getWorkingarea().toLowerCase());
        }
        if(customer.getCustcountry() != null)
        {
            currCust.setCustcountry(customer.getCustcountry().toLowerCase());
        }
        if(customer.getGrade() != null)
        {
            currCust.setGrade(customer.getGrade().toLowerCase());
        }
        if(customer.hasvalueforopeningamt)
        {
            currCust.setOpeningamt(customer.getOpeningamt());
        }
        if(customer.hasvalueforrecvamt)
        {
            currCust.setReceiveamt(customer.getReceiveamt());
        }
        if(customer.hasvalueforpaymentamt)
        {
            currCust.setPaymentamt(customer.getPaymentamt());
        }
        if(customer.hasvalueforoutstandingamt)
        {
            currCust.setOutstandingamt(customer.getOutstandingamt());
        }
        if(customer.getPhone() != null)
        {
            currCust.setPhone(customer.getPhone());
        }
        if(customer.getAgent() != null)
        {
            Agents currentAgent = agentsService.findAgentByCode(customer.getAgent().getAgentcode());
            currCust.setAgent(currentAgent);
        }

        return custrepos.save(currCust);
    }
}
