package io.skysail.service.monitor.hello;

import io.skysail.service.monitor.domain.Measurement;
import io.skysail.service.monitor.domain.Monitor;
import io.skysail.service.monitor.repositories.MeasurementRepository;
import io.skysail.service.monitor.repositories.MonitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;

@Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {

    @Autowired
    private MeasurementRepository measurementRepository;

    @Autowired
    private MonitorRepository monitorRepository;


//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTime() {

        System.out.println("making new measurements at " + new Date());
        monitorRepository.findAll().forEach(monitor -> {
            makeMeasurement(monitor);
        });
    }

    private void makeMeasurement(Monitor monitor) {
        long start = System.currentTimeMillis();
        Measurement m = new Measurement();
        m.setMonitor(monitor);
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

    @GetMapping(path = "/m")
    public @ResponseBody
    Iterable<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }


}
