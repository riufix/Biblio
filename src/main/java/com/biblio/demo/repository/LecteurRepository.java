package com.biblio.demo.repository;

import com.biblio.demo.model.Lecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecteurRepository extends JpaRepository<Lecteur, Integer> {
}
