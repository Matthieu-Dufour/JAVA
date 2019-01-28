package org.lpro.categoriesandwich.boundary;

import org.lpro.categoriesandwich.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieResource extends JpaRepository<Categorie, String> {

}