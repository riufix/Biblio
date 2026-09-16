package com.biblio.demo.repository;

import com.biblio.demo.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuteurRepository extends JpaRepository<Auteur, Integer>  {
}
