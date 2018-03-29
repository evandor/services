package io.skysail.service.monitor.repositories;

import io.skysail.service.monitor.domain.Monitor;
import org.springframework.data.repository.CrudRepository;

public interface MonitorRepository extends CrudRepository<Monitor, Long> {

}
