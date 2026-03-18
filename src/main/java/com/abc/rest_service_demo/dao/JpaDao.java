package com.abc.rest_service_demo.dao;

import com.abc.rest_service_demo.model.UserJpaModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class JpaDao {

    @PersistenceContext
    EntityManager entityManager;

    public UserJpaModel getUserById(String id){
        return entityManager.find(UserJpaModel.class, id);
    }

    public UserJpaModel insertUser(UserJpaModel user){
        return entityManager.merge(user);
    }

    public UserJpaModel updateUser(UserJpaModel user){
        return entityManager.merge(user);
    }
    public void deleteUser(String id){
        UserJpaModel user = getUserById(id);
        if(user != null){
            entityManager.remove(user);
        }
    }

    public void deleteAllUsers(){
        entityManager.createQuery("DELETE FROM UserJpaModel").executeUpdate();
    }

    public List<UserJpaModel> getAllUsers() {
        TypedQuery<UserJpaModel> typedQuery = entityManager.createQuery("find_all_users", UserJpaModel.class);
        return typedQuery.getResultList();
    }

}
