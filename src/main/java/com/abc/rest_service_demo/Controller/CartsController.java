package com.abc.rest_service_demo.Controller;

import com.abc.rest_service_demo.Service.DBSimulatedService;
import com.abc.rest_service_demo.model.Item;
import com.abc.rest_service_demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class CartsController {

    @Autowired
    private DBSimulatedService dbSimulatedService;

    @PostMapping(path = "/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        try{
            log.info("received request for user"+user);

            User createdUser = dbSimulatedService.createUser(user);
            createdUser.setPassword(null);

            return ResponseEntity.ok(createdUser);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }

    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId){
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

     @PostMapping(path = "/user/{userId}/carts")
        public ResponseEntity<Item> createCarts(@PathVariable("userId") String userId, @RequestBody Item item){
            try{
                Item createdItem = dbSimulatedService.createItem(userId, item);
                if(createdItem != null) {
                    return ResponseEntity.ok(createdItem);
                }
                    return ResponseEntity.notFound().build();

            }catch (Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }

     @GetMapping(path = "/user/{userId}/carts")
        public ResponseEntity<List<Item>> getAllItemsByUserId(@PathVariable("userId") String userId){
            try{
                List<Item> itemList = dbSimulatedService.getAllItemsByUserId(userId);
                if(itemList != null) {
                    return ResponseEntity.ok(itemList);
                }
                    return ResponseEntity.notFound().build();

            }catch (Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }

        @PutMapping(path = "/user/{userId}/cart/{itemId}")
        public ResponseEntity<Item> updateItemByItemIdForUser(@PathVariable("userId") String userId, @PathVariable("itemId") String itemId, @RequestBody Item item){
            try{
                Item updatedItem = dbSimulatedService.updateItemById(userId, itemId, item);
                if(updatedItem != null) {
                    return ResponseEntity.ok(updatedItem);
                }
                    return ResponseEntity.notFound().build();

            }catch (Exception e){
                return ResponseEntity.internalServerError().build();
            }
        }

        @DeleteMapping(path = "/user/{userId}/cart/{itemId}")
        public ResponseEntity<String> deleteItemByItemIdForUser(@PathVariable("userId") String userId, @PathVariable("itemId") String itemId){
            try{
                boolean isDeleted = dbSimulatedService.deleteItemById(userId, itemId);
                if(isDeleted) {
                    return ResponseEntity.ok("Item deleted successfully");
                }
                    return ResponseEntity.status(404).body("Item not found");

            }catch (Exception e){
                return ResponseEntity.internalServerError().body("Error deleting item");
            }
        }

        @DeleteMapping(path = "/user/{userId}/carts")
        public ResponseEntity<String> deleteUserByUserId(@PathVariable("userId") String userId){
            try{
                boolean isDeleted = dbSimulatedService.deleteAllItemsByUserId(userId);
                if(isDeleted) {
                    return ResponseEntity.ok("Items deleted successfully");
                }
                    return ResponseEntity.status(404).body("Items not found");

            }catch (Exception e){
                return ResponseEntity.internalServerError().body("Error deleting Items");
            }
        }


}
