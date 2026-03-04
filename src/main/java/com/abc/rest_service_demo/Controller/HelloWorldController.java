package com.abc.rest_service_demo.Controller;

import com.abc.rest_service_demo.DTO.HelloWorldDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping(path = "/hello/{name}")
    public String helloWorldWithParam(@PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping(path = "/users/{id}")
    public HelloWorldDTO getUserById(@PathVariable String id) {
        // In a real application, you would fetch the user from a database
        // Here we are just returning a dummy user for demonstration purposes
        return new HelloWorldDTO("user" + id, "FirstName" + id, "LastName" + id, "user" + id + "@example.com", 25);
    }
}
