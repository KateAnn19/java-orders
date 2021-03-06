package local.kmcgeeka.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customers
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long custcode;

    @Column(nullable = false, unique = true)
    private String custname;

    private String custcity;

    private String workingarea;

    private String custcountry;

    private String grade;

    @Transient
    public boolean hasvalueforopeningamt = false;
    private double openingamt;

    @Transient
    public boolean hasvalueforrecvamt = false;
    private double receiveamt;

    @Transient
    public boolean hasvalueforpaymentamt = false;
    private double paymentamt;

    @Transient
    public boolean hasvalueforoutstandingamt = false;
    private double outstandingamt;

    private String phone;

//    AGENTCODE Long foreign key (one agent to many customers) not null
    @ManyToOne
    @JoinColumn(name = "agentcode", nullable = false)
    @JsonIgnoreProperties(value = "customers")
    private Agents agent; //we need an agent associated with each customer

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "customer")
    private List<Orders> orders = new ArrayList<>();


    public Customers()
    {
    }//default

    public Customers(
        String custname,
        String custcity,
        String workingarea,
        String custcountry,
        String grade,
        double openingamt,
        double receiveamt,
        double paymentamt,
        double outstandingamt,
        String phone,
        Agents agent)
    {
        this.custname = custname;
        this.custcity = custcity;
        this.workingarea = workingarea;
        this.custcountry = custcountry;
        this.grade = grade;
        this.openingamt = openingamt;
        this.receiveamt = receiveamt;
        this.paymentamt = paymentamt;
        this.outstandingamt = outstandingamt;
        this.phone = phone;
        this.agent = agent;
    }

    public long getCustcode()
    {
        return custcode;
    }

    public void setCustcode(long custcode)
    {
        this.custcode = custcode;
    }

    public String getCustname()
    {
        return custname;
    }

    public void setCustname(String custname)
    {
        this.custname = custname;
    }

    public String getCustcity()
    {
        return custcity;
    }

    public void setCustcity(String custcity)
    {
        this.custcity = custcity;
    }

    public String getWorkingarea()
    {
        return workingarea;
    }

    public void setWorkingarea(String workingarea)
    {
        this.workingarea = workingarea;
    }

    public String getCustcountry()
    {
        return custcountry;
    }

    public void setCustcountry(String custcountry)
    {
        this.custcountry = custcountry;
    }

    public String getGrade()
    {
        return grade;
    }

    public void setGrade(String grade)
    {
        this.grade = grade;
    }

    public double getOpeningamt()
    {
        return openingamt;
    }

    public void setOpeningamt(double openingamt)
    {
        this.hasvalueforopeningamt = true;
        this.openingamt = openingamt;
    }

    public double getReceiveamt()
    {
        return receiveamt;
    }

    public void setReceiveamt(double receiveamt)
    {
        this.hasvalueforrecvamt = true;
        this.receiveamt = receiveamt;
    }

    public double getPaymentamt()
    {
        return paymentamt;
    }

    public void setPaymentamt(double paymentamt)
    {
        this.hasvalueforpaymentamt = true;
        this.paymentamt = paymentamt;
    }

    public double getOutstandingamt()
    {
        return outstandingamt;
    }

    public void setOutstandingamt(double outstandingamt)
    {
        this.hasvalueforoutstandingamt = true;
        this.outstandingamt = outstandingamt;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Agents getAgent()
    {
        return agent;
    }

    public void setAgent(Agents agent)
    {
        this.agent = agent;
    }

    public List<Orders> getOrders()
    {
        return orders;
    }

    public void setOrders(List<Orders> orders)
    {
        this.orders = orders;
    }

    @Override
    public String toString()
    {
        return "Customers{" +
            "custcode=" + custcode +
            ", custname='" + custname + '\'' +
            ", custcity='" + custcity + '\'' +
            ", workingarea='" + workingarea + '\'' +
            ", custcountry='" + custcountry + '\'' +
            ", grade='" + grade + '\'' +
            ", openingamt=" + openingamt +
            ", receiveamt=" + receiveamt +
            ", paymentamt=" + paymentamt +
            ", outstandingamt=" + outstandingamt +
            ", phone='" + phone + '\'' +
            ", agent=" + agent +
            ", orders=" + orders +
            '}';
    }
}
