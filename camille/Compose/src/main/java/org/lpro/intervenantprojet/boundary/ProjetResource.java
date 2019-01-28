package org.lpro.intervenantprojet.boundary;

import java.util.List;

import org.lpro.intervenantprojet.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetResource extends JpaRepository<Projet, String> {

    List<Projet> findByIntervenantId(String intervenantId);

}