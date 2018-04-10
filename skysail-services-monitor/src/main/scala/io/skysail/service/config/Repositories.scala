package io.skysail.service.config

import org.springframework.data.repository.CrudRepository

trait MonitorRepository extends CrudRepository[Monitor, Long]

trait MeasurementRepository extends CrudRepository[Measurement, Long]
