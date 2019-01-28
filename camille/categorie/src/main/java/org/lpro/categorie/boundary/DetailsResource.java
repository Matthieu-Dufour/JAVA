package org.lpro.categorie.boundary;

import org.lpro.categorie.entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsResource extends JpaRepository<Details, String> {

}