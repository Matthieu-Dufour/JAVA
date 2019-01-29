/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lpro.categorieservice.boundary;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.lpro.categorieservice.entity.Sandwich;
/**
 *
 * @author dufour76u
 */
public interface SandwichResource extends CrudRepository<Sandwich,String> {
	List<Sandwich> findByCategoryId(String categoryId);
}
