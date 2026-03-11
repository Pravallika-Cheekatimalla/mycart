package com.abc.rest_service_demo.Service;
import com.abc.rest_service_demo.model.Item;
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

    public Item createItem(String userId, Item item){
        User user = getUserById(userId);
        if(user == null){
            return null;
        }
        List<Item> itemList = user.getItemList();
        item.setId(UUID.randomUUID().toString());
        itemList.add(item);
        user.setItemList(itemList);
        return item;
    }

    public List<Item> getAllItemsByUserId(String userId){
        User user = getUserById(userId);
        if(user == null){
            return null;
        }
        return user.getItemList();
    }

    public Item updateItemById(String userId, String itemId, Item item){
        User user = getUserById(userId);
        if(user == null){
            return null;
        }
        Item storedItem = null;
        List<Item> itemList = user.getItemList();
       for(Item i:itemList){
              if(i.getId().equals(itemId)){
               storedItem = i;
               break;
              }
       }
         if(storedItem == null){
              return null;
         }
            storedItem.setName(item.getName());
            storedItem.setDescription(item.getDescription());
            storedItem.setQuantity(item.getQuantity());
//            itemList.add(storedItem);
//            user.setItemList(itemList);

        return storedItem;
    }

    public boolean deleteItemById(String userId, String itemId){
        User user = getUserById(userId);
        if(user == null){
            return false;
        }
        List<Item> itemList = user.getItemList();
        for(Item i:itemList){
            if(i.getId().equals(itemId)){
                itemList.remove(i);
                user.setItemList(itemList);
                return true;
            }
        }
        return false;
    }

    public boolean deleteAllItemsByUserId(String userId){
        User user = getUserById(userId);
        if(user == null){
            return false;
        }
        user.setItemList(new ArrayList<>());
        return true;
    }

}
