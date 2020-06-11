package local.kmcgeeka.javaorders.repositories;

import local.kmcgeeka.javaorders.models.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long>
{

}
