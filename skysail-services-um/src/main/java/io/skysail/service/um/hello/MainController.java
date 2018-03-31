package io.skysail.service.um.hello;

import io.skysail.service.um.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/um")
public class MainController {

    @Autowired
    private UserRepository userRepository;


//    @GetMapping(path = "/all")
//    public @ResponseBody
//    Iterable<User> getAllUsers() {
//        // This returns a JSON or XML with the users
//        return userRepository.findAll();
//    }


//    @GetMapping(path = "/m")
//    public @ResponseBody
//    Iterable<Measurement> getAllMeasurements() {
//        return measurementRepository.findAll();
//    }


}
