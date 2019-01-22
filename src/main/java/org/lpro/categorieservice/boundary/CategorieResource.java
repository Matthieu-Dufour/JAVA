package org.lpro.categorieservice.boundary;

import org.lpro.categorieservice.entity.Categorie;
import org.springframework.data.repository.CrudRepository;

public interface CategorieResource extends CrudRepository<Categorie, String> {
    
}
