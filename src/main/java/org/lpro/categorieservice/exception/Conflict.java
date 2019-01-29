/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.lpro.categorieservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author dufour76u
 */
@ResponseStatus(code=HttpStatus.CONFLICT)
public class Conflict extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Conflict(String message) {
        super(message);
    }
    
    public Conflict(String message, Throwable cause) {
        super(message, cause);
    }
}
