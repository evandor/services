package io.skysail.service.monitor.controller;

import io.skysail.service.monitor.domain.ConnectionResult;
import io.skysail.service.monitor.domain.Measurement;
import io.skysail.service.monitor.domain.Monitor;
import io.skysail.service.monitor.repositories.MeasurementRepository;
import io.skysail.service.monitor.repositories.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private MonitorRepository monitorRepository;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        monitorRepository.findAll().forEach(this::makeMeasurement);
    }

    private void makeMeasurement(Monitor monitor) {
        long start = System.currentTimeMillis();
        Measurement m = new Measurement();
        m.setMonitor(monitor.getId());
        m.setTimestamp(new Date().getTime());
        m.setName("hi");

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL("http://demo.test.skysail.io").openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                m.setResult(ConnectionResult.FAILURE);
            } else {
                m.setResult(ConnectionResult.SUCCESS);
            }
        } catch (UnknownHostException uhe) {
            m.setResult(ConnectionResult.UNKNOWN_HOST);
        } catch (IOException e) {
            e.printStackTrace();
            m.setResult(ConnectionResult.TIMEOUT);
        }

        m.setDuration(System.currentTimeMillis() - start);
        measurementRepository.save(m);
    }

    @GetMapping(path = "/measurements")
    public @ResponseBody
    Iterable<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }

    @GetMapping(path = "/measurements/{id}")
    public @ResponseBody
    Optional<Measurement> getMeasurement(@PathVariable Long id) {
        return measurementRepository.findById(id);
    }

    @GetMapping(path = "/monitors")
    public @ResponseBody
    Iterable<Monitor> getAllMonitors() {
        return monitorRepository.findAll();
    }

    @PostMapping(path = "/monitors/")
    public void post(@ModelAttribute("monitor") Monitor monitor) {
        monitorRepository.save(monitor);
    }

    @GetMapping(path = "/monitors/{id}")
    public @ResponseBody
    Optional<Monitor> getMonitor(@PathVariable Long id) {
        return monitorRepository.findById(id);
    }

    @PutMapping(path = "/monitors/{id}/")
    public void put(@ModelAttribute("monitor") Monitor monitor) {
        monitorRepository.save(monitor);
    }

    @DeleteMapping(path = "/monitors/{id}/")
    public void delete(@PathVariable Long id) {
        monitorRepository.deleteById(id);
    }
}
