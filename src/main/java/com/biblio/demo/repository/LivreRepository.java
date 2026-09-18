package com.biblio.demo.repository;

import com.biblio.demo.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {

    // Spring Data ecrit la requete : select * from livre where auteur_id = ?
    List<Livre> findByAuteurId(int auteurId);
}
