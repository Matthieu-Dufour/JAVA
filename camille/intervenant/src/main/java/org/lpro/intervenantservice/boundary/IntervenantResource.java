package org.lpro.intervenantservice.boundary;

import org.lpro.intervenantservice.entity.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntervenantResource extends JpaRepository<Intervenant, String> {

}