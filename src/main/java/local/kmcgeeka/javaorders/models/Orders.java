package local.kmcgeeka.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")

public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(nullable = false)
    private long ordnum;
    private double ordamount;
    private double advanceamount;
    private String orderdescription;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    @JsonIgnoreProperties(value = "orders")
    private Customers customer;

    //    ORDERNUM foreign key to ORDERS
    //    PAYMENTID foreign key to PAYMENTS.

    @ManyToMany()
    @JoinTable(name = "orderspayments",
        joinColumns = @JoinColumn(name = "ordnum"),
        inverseJoinColumns = @JoinColumn(name = "paymentid"))
    @JsonIgnoreProperties(value = "orders")
    private List<Payments> payments = new ArrayList<>();

    public Orders()
    {
    }//default

    public Orders(
        double ordamount,
        double advanceamount,
        String orderdescription,
        Customers customer)
    {
        this.ordamount = ordamount;
        this.advanceamount = advanceamount;
        this.orderdescription = orderdescription;
        this.customer = customer;
    }

    public long getOrdnum()
    {
        return ordnum;
    }

    public void setOrdnum(long ordnum)
    {
        this.ordnum = ordnum;
    }

    public double getOrdamount()
    {
        return ordamount;
    }

    public void setOrdamount(double ordamount)
    {
        this.ordamount = ordamount;
    }

    public double getAdvanceamount()
    {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount)
    {
        this.advanceamount = advanceamount;
    }

    public String getOrderdescription()
    {
        return orderdescription;
    }

    public void setOrderdescription(String orderdescription)
    {
        this.orderdescription = orderdescription;
    }

    public Customers getCustomer()
    {
        return customer;
    }

    public void setCustomer(Customers customer)
    {
        this.customer = customer;
    }

    public List<Payments> getPayments()
    {
        return payments;
    }

    public void setPayments(List<Payments> payments)
    {
        this.payments = payments;
    }

    @Override
    public String toString()
    {
        return "Orders{" +
            "ordnum=" + ordnum +
            ", ordamount=" + ordamount +
            ", advanceamount=" + advanceamount +
            ", orderdescription='" + orderdescription + '\'' +
            ", customer=" + customer +
            ", payments=" + payments +
            '}';
    }
}
