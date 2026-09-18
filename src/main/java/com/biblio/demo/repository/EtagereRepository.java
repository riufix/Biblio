package com.biblio.demo.repository;

import com.biblio.demo.model.Etagere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtagereRepository extends JpaRepository<Etagere, Integer> {
}
