package com.example.xmlexercise.repository;

import com.example.xmlexercise.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u where" +
            "(select count(p) from Product p where p.seller.id = u.id and p.buyer is not null)>0" +
            " order by u.lastName,u.firstName")
    List<User> findAllUsersWihMoreThanOneProduct();
}
