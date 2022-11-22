package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    public List<User> findByNomeAndCognome(String nome, String cognome);
}
