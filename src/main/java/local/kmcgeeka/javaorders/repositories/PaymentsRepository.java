package local.kmcgeeka.javaorders.repositories;

import local.kmcgeeka.javaorders.models.Payments;
import org.springframework.data.repository.CrudRepository;

public interface PaymentsRepository extends CrudRepository<Payments, Long>
{
    //custom methods only


}
