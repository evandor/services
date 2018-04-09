package io.skysail.service.um.hello;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/docker")
public class MainController {

    private DockerClient dockerClient = DockerClientBuilder.getInstance().build();

    @GetMapping(path = "/container")
    public @ResponseBody
    Iterable<Container> getAllUsers() {
        List<Container> containers = dockerClient.listContainersCmd().withShowAll(true).exec();
        // This returns a JSON or XML with the users
        return containers;
    }


//    @GetMapping(path = "/m")
//    public @ResponseBody
//    Iterable<Measurement> getAllMeasurements() {
//        return measurementRepository.findAll();
//    }


}
