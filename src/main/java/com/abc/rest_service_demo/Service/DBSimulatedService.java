package com.abc.rest_service_demo.Service;
import com.abc.rest_service_demo.model.User;
import org.springframework.stereotype.Service;


import java.util.*;
@Service
public class DBSimulatedService {

    Map<String, User> map = new HashMap<>();

    public User createUser(User user){
        user.setId(UUID.randomUUID().toString());
        user.setItemList(new ArrayList<>());

        map.put(user.getId(), user);
        return user;
    }

    public User getUserById(String id){
        return handleUser(map.get(id));
    }

    public User handleUser(User user){
        if(user == null){
            return null;
        }
        user.setPassword(null);
        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        for (User user : map.values()) {
            userList.add(handleUser(user));
        }
        return userList;
    }

}
