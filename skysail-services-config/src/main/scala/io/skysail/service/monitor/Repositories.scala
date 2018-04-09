package io.skysail.service.monitor

import org.springframework.data.repository.CrudRepository

trait MonitorRepository extends CrudRepository[Monitor, Long]

trait MeasurementRepository extends CrudRepository[Measurement, Long]
