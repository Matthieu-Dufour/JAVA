/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lpro.categorieservice.boundary;

import org.springframework.data.repository.CrudRepository;

import org.lpro.categorieservice.entity.Category;
/**
 *
 * @author dufour76u
 */
public interface CategoryResource extends CrudRepository<Category,String> {
}
