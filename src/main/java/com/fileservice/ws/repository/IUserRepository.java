package com.fileservice.ws.repository;

import com.fileservice.ws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User,Long> {
}
