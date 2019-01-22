package org.lpro.categorieservice.boundary;

import java.util.List;
import org.lpro.categorieservice.entity.Sandwich;
import org.springframework.data.repository.CrudRepository;

public interface SandwichResource extends CrudRepository<Sandwich, String> {
    
    List<Sandwich> findByCategorieId(String categorieId);
    
}
