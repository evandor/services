package io.skysail.service.monitor

import java.io.IOException
import java.net.{HttpURLConnection, URL, UnknownHostException}
import java.util.{Date, Optional}

import io.skysail.service.monitor.domain.{ConnectionResult, Measurement, Monitor}
import io.skysail.service.monitor.repositories.{MeasurementRepository, MonitorRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._

import scala.collection.JavaConverters._

@Controller
@RequestMapping(path = Array("/monitor"))
class MonitorController @Autowired()() {

  @Autowired private val measurementRepository: MeasurementRepository = null

  @Autowired private val monitorRepository: MonitorRepository = null

  @Scheduled(fixedRate = 60000) def reportCurrentTime(): Unit = {
    monitorRepository.findAll.asScala.foreach(makeMeasurement)
  }

  @GetMapping(path = Array("/measurements"))
  @ResponseBody def getAllMeasurements: Iterable[Measurement] = measurementRepository.findAll.asScala

  @GetMapping(path = Array("/measurements/{id}"))
  @ResponseBody def getMeasurement(@PathVariable id: Long): Optional[Measurement] = measurementRepository.findById(id)

  @GetMapping(path = Array("/monitors"))
  @ResponseBody def getAllMonitors: Iterable[Monitor] = monitorRepository.findAll.asScala

  @PostMapping(path = Array("/monitors/"))
  def post(@ModelAttribute("monitor") monitor: Monitor): Monitor =  monitorRepository.save(monitor)

  @GetMapping(path = Array("/monitors/{id}"))
  @ResponseBody
  def getMonitor(@PathVariable id: Long): Optional[Monitor] = monitorRepository.findById(id)

  @PutMapping(path = Array("/monitors/{id}/"))
  def put(@ModelAttribute("monitor") monitor: Monitor): Monitor = monitorRepository.save(monitor)

  @DeleteMapping(path = Array("/monitors/{id}/"))
  def delete(@PathVariable id: Long): Unit = monitorRepository.deleteById(id)

  private def makeMeasurement(monitor: Monitor): Unit = {
    val start = System.currentTimeMillis
    val m = new Measurement
    m.setMonitor(monitor.getId)
    m.setTimestamp(new Date().getTime)
    m.setName("hi")
    var connection: HttpURLConnection = null
    try {
      connection = new URL("http://demo.test.skysail.io").openConnection.asInstanceOf[HttpURLConnection]
      connection.setRequestMethod("HEAD")
      val responseCode = connection.getResponseCode
      if (responseCode != 200) m.setResult(ConnectionResult.FAILURE)
      else m.setResult(ConnectionResult.SUCCESS)
    } catch {
      case uhe: UnknownHostException =>
        m.setResult(ConnectionResult.UNKNOWN_HOST)
      case e: IOException =>
        e.printStackTrace()
        m.setResult(ConnectionResult.TIMEOUT)
    }
    m.setDuration(System.currentTimeMillis - start)
    measurementRepository.save(m)
  }

}