package io.skysail.service.monitor.repositories;

import io.skysail.service.monitor.domain.Measurement;
import org.springframework.data.repository.CrudRepository;

public interface MeasurementRepository extends CrudRepository<Measurement, Long> {

}
