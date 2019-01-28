package org.lpro.categorie.boundary;

import org.lpro.categorie.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieResource extends JpaRepository<Categorie, String> {

}