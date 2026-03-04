package com.abc.rest_service_demo.Controller;

import com.abc.rest_service_demo.Service.DBSimulatedService;
import com.abc.rest_service_demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CartsController {

    @Autowired
    private DBSimulatedService dbSimulatedService;

    @PostMapping(path = "/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{

            User createdUser = dbSimulatedService.createUser(user);
            createdUser.setPassword(null);

            return ResponseEntity.ok(createdUser);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        try{
           User createdUser = dbSimulatedService.getUserById(userId);
           if(createdUser != null) {
               return ResponseEntity.ok(createdUser);
           } else {
               return ResponseEntity.notFound().build();
           }
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/users")
    public List<User> getAllUsers(){
       return dbSimulatedService.getAllUsers();
     }

}
