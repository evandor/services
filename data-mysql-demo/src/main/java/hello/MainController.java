package hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.User;
import hello.UserRepository;

import java.util.Date;

@Controller
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MeasurementRepository measurementRepository;

    @GetMapping(path = "/add") // Map ONLY GET Requests
    public @ResponseBody
    String addNewUser(@RequestParam String name
            , @RequestParam String email) {

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }

    @Scheduled(fixedRate = 10000)
    public void reportCurrentTime() {

        Measurement m = new Measurement();
        m.setName("hi");
        m.setDuration(1L);
        m.setTimestamp(new Date().getTime());
        measurementRepository.save(m);

        System.out.println("The time is now " + new Date());
    }

    @GetMapping(path = "/m")
    public @ResponseBody
    Iterable<Measurement> getAllMeasurements() {
        return measurementRepository.findAll();
    }


}
