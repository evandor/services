package hello;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface MeasurementRepository extends PagingAndSortingRepository<Measurement, Long> {

	//List<Person> findByLastName(@Param("name") String name);

}
