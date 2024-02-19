package com.namsi.Bankapp.repository;

import com.namsi.Bankapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
     Boolean existsByEmail(String email);


}
