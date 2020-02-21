package com.othman.MaBanque.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.othman.MaBanque.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{

}
