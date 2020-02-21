package com.othman.MaBanque.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.othman.MaBanque.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte,String> {


}
