package com.abc.rest_service_demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
@NamedQuery(name = "find_all_users",
        query = "SELECT u FROM UserJpaModel u")
public class UserJpaModel {

    @Id
    @UuidGenerator
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private int age;

    public UserJpaModel() {}

    public UserJpaModel(UUID id, String firstName, String lastName, String email, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public UserJpaModel(String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

}
