package com.target.shipments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.target.shipments.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}

