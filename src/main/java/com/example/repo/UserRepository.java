package com.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{}

