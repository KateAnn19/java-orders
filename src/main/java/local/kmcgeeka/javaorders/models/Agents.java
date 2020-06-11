package local.kmcgeeka.javaorders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agents")

public class Agents
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long agentcode;
//    @Column(nullable = false, unique = true)
    private String agentname;
    private String workingarea;
    private String phone;
    private String country;
    private double commission;

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = "agent")
    private List<Customers> customers = new ArrayList<>();

    public Agents()
    {
    }//default constructor (just have to have this)

    public Agents(
        String agentname,
        String workingarea,
        String phone,
        String country,
        double commission,
        List<Customers> customers)
    {
        this.agentname = agentname;
        this.workingarea = workingarea;
        this.phone = phone;
        this.country = country;
        this.commission = commission;
        this.customers = customers;
    }

    public long getAgentcode()
    {
        return agentcode;
    }

    public void setAgentcode(long agentcode)
    {
        this.agentcode = agentcode;
    }

    public String getAgentname()
    {
        return agentname;
    }

    public void setAgentname(String agentname)
    {
        this.agentname = agentname;
    }

    public String getWorkingarea()
    {
        return workingarea;
    }

    public void setWorkingarea(String workingarea)
    {
        this.workingarea = workingarea;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public double getCommission()
    {
        return commission;
    }

    public void setCommission(double commission)
    {
        this.commission = commission;
    }

    public List<Customers> getCustomers()
    {
        return customers;
    }

    public void setCustomers(List<Customers> customers)
    {
        this.customers = customers;
    }

    @Override
    public String toString()
    {
        return "Agents{" +
            "agentcode=" + agentcode +
            ", agentname='" + agentname + '\'' +
            ", workingarea='" + workingarea + '\'' +
            ", phone='" + phone + '\'' +
            ", country='" + country + '\'' +
            ", commission=" + commission +
            ", customers=" + customers +
            '}';
    }
}
