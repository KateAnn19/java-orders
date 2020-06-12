package local.kmcgeeka.javaorders.repositories;
import local.kmcgeeka.javaorders.models.Customers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customers, Long>
{
    List<Customers> findByCustnameContainingIgnoringCase(String likename);
}





