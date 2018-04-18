package com.fpm.registry.repositories;

import com.fpm.registry.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
