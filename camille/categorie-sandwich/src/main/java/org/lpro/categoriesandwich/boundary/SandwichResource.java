package org.lpro.categoriesandwich.boundary;

import java.util.List;

import org.lpro.categoriesandwich.entity.Sandwich;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SandwichResource extends JpaRepository<Sandwich, String> {

    List<Sandwich> findByCategorieId(String categorieId);

}